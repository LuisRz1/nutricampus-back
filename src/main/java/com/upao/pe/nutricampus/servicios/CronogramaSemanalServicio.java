package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.*;
import com.upao.pe.nutricampus.repositorios.CronogramaSemanalRepositorio;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CrearCronogramaSemanalRequest;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CronogramaSemanalSerializer;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.EditarCronogramaSemanalRequest;
import com.upao.pe.nutricampus.serializers.dieta.Dieta;
import com.upao.pe.nutricampus.serializers.dieta.DietaSerializer;
import com.upao.pe.nutricampus.serializers.rutina.RutinaSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CronogramaSemanalServicio {

    @Autowired private CronogramaSemanalRepositorio cronogramaSemanalRepositorio;
    @Autowired private UsuarioServicio usuarioServicio;
    @Autowired private RutinaServicio rutinaServicio;
    @Autowired private RestTemplate restTemplate;
    @Value("${dieta.service.url}")
    private String url;

    // READ
    public List<CronogramaSemanalSerializer> listarCronogramaSemanal(){return cronogramaSemanalRepositorio.findAll().stream().map(this::retornarCronogramaSemanalSerializer).toList();}

    // CREATE
    public CronogramaSemanalSerializer crearCronogramaSemanal(CrearCronogramaSemanalRequest request){
        CronogramaSemanal cronogramaSemanal = new CronogramaSemanal(null, request.getFechaInicio(), request.getFechaFin(), request.getDia(), false, null, null, null);
        cronogramaSemanalRepositorio.save(cronogramaSemanal);
        // Generar relación entre Cronograma y Rutinas
        List<RutinaCronograma> rutinas = new ArrayList<>();
        for(Long id: request.getIdRutinas()){
            Rutina rutina = rutinaServicio.buscarRutina(id);
            RutinaCronograma rutinaCronograma = new RutinaCronograma(null, cronogramaSemanal, rutina);
            rutinas.add(rutinaCronograma);
        }

        // Generar relación entre Cronograma y Dietas
        List<DietaCronograma> dietas = new ArrayList<>();
        for(Long id: request.getIdDietas()){
            Dieta dieta = restTemplate.getForObject(url+"/dieta/buscar/"+id, Dieta.class);
            DietaCronograma dietaCronograma = new DietaCronograma(null, cronogramaSemanal, dieta);
            dietas.add(dietaCronograma);
        }

        // Editar cronograma creado
        cronogramaSemanal.setRutinaCronogramas(rutinas);
        cronogramaSemanal.setDietaCronogramas(dietas);
        cronogramaSemanalRepositorio.saveAndFlush(cronogramaSemanal);

        // Creando relacion con el usuario
        Usuario usuario = usuarioServicio.buscarPorNombreUsuario(request.getNombreUsuario());
        CronogramaUsuario cronogramaUsuario = new CronogramaUsuario(null, usuario, cronogramaSemanal);
        usuario.getCronogramaUsuario().add(cronogramaUsuario);
        usuarioServicio.actualizarCambios(usuario);
        return retornarCronogramaSemanalSerializer(cronogramaSemanal);
    }

    // UPDATE
    public CronogramaSemanalSerializer editarCronogramaSemanal(Long id, EditarCronogramaSemanalRequest request){
        CronogramaSemanal cronogramaSemanal = buscarCronogramaSemanal(id);

        cronogramaSemanal.setFechaFin(request.getFechaInicio());
        cronogramaSemanal.setFechaFin(request.getFechaFin());
        cronogramaSemanal.setDia(request.getDia());
        cronogramaSemanal.setCompletado(request.isCompletado());
        cronogramaSemanalRepositorio.saveAndFlush(cronogramaSemanal);
        return retornarCronogramaSemanalSerializer(cronogramaSemanal);
    }

    // DELETE
    public List<CronogramaSemanalSerializer> eliminarCronogramaSemanal(Long id){
        Optional<CronogramaSemanal> cronogramaSemanal = cronogramaSemanalRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        }
        cronogramaSemanalRepositorio.delete(cronogramaSemanal.get());
        return listarCronogramaSemanal();
    }

    // Mapear a Serializer
    public CronogramaSemanalSerializer retornarCronogramaSemanalSerializer(CronogramaSemanal cronogramaSemanal){
        List<RutinaSerializer> rutinas = new ArrayList<>();
        for(int i = 0; i < cronogramaSemanal.getRutinaCronogramas().size(); i++){
            rutinas.add(rutinaServicio.retornarRutinaSerializer(cronogramaSemanal.getRutinaCronogramas().get(i).getRutina()));
        }

        List<DietaSerializer> dietas = new ArrayList<>();
        for(int i = 0; i < cronogramaSemanal.getDietaCronogramas().size(); i++){
            dietas.add(restTemplate.postForObject(url+"/dieta/serializer/", cronogramaSemanal.getDietaCronogramas().get(i).getDieta(), DietaSerializer.class));
        }
        return new CronogramaSemanalSerializer(cronogramaSemanal.getFechaInicio(), cronogramaSemanal.getFechaFin(), cronogramaSemanal.getDia(), cronogramaSemanal.isCompletado(), rutinas, dietas);
    }

    // Buscar crongorama
    public CronogramaSemanal buscarCronogramaSemanal(Long id){
        Optional<CronogramaSemanal> cronogramaSemanal = cronogramaSemanalRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        } return cronogramaSemanal.get();
    }
}