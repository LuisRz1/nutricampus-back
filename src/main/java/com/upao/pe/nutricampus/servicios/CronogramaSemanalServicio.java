package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.CronogramaSemanal;
import com.upao.pe.nutricampus.modelos.CronogramaUsuario;
import com.upao.pe.nutricampus.modelos.Rutina;
import com.upao.pe.nutricampus.modelos.Usuario;
import com.upao.pe.nutricampus.repositorios.CronogramaSemanalRepositorio;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CrearCronogramaSemanalRequest;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CronogramaSemanalSerializer;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.EditarCronogramaSemanalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CronogramaSemanalServicio {

    @Autowired private CronogramaSemanalRepositorio cronogramaSemanalRepositorio;
    @Autowired private UsuarioServicio usuarioServicio;
    //@Autowired private RutinaServicio rutinaServicio;
    //@Autowired private RestTemplate restTemplate;
    //@Value("${dieta.service.url}")
    //private String url;

    // READ
    public List<CronogramaSemanalSerializer> listarCronogramaSemanal(){return cronogramaSemanalRepositorio.findAll().stream().map(this::retornarCronogramaSemanalSerializer).toList();}

    // CREATE
    public CronogramaSemanalSerializer crearCronogramaSemanal(CrearCronogramaSemanalRequest request){
        CronogramaSemanal cronogramaSemanal = new CronogramaSemanal(null, request.getFechaInicio(), request.getFechaFin(), request.getDia(), false, null);
        cronogramaSemanalRepositorio.save(cronogramaSemanal);
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
        return new CronogramaSemanalSerializer(cronogramaSemanal.getFechaInicio(), cronogramaSemanal.getFechaFin(), cronogramaSemanal.getDia(), cronogramaSemanal.isCompletado());
    }

    // Buscar crongorama
    public CronogramaSemanal buscarCronogramaSemanal(Long id){
        Optional<CronogramaSemanal> cronogramaSemanal = cronogramaSemanalRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        } return cronogramaSemanal.get();
    }
}