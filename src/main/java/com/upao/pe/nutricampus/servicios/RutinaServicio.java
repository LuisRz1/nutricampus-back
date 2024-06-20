package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import com.upao.pe.nutricampus.modelos.EjercicioRutina;
import com.upao.pe.nutricampus.modelos.Rutina;
import com.upao.pe.nutricampus.repositorios.RutinaRepositorio;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDia;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDiaSerializer;
import com.upao.pe.nutricampus.serializers.rutina.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RutinaServicio {

    @Autowired private RutinaRepositorio rutinaRepositorio;
    @Autowired private EjercicioServicio ejercicioServicio;
    @Autowired private RestTemplate restTemplate;
    @Value("${dieta.service.url}")
    private String url;

    // READ
    public List<RutinaSerializer> listarRutinas(){return rutinaRepositorio.findAll().stream().map(this::retornarRutinaSerializer).toList();}

    // CREATE
    public RutinaSerializer crearRutina(CrearRutinaRequest request){
        Rutina rutina = new Rutina(null, request.getTiempo(), null, null);
        rutinaRepositorio.save(rutina);
        // Generar la lista de la tabla intermedia EjercicioRutina
        List<EjercicioRutina> ejercicioRutinas = new ArrayList<>();

        for(EjercicioConHoraCrear ejercicioConHora : request.getEjercicios()){
            Ejercicio ejercicio = ejercicioServicio.buscarEjercicio(ejercicioConHora.getEjercicio());
            HoraDiaSerializer horaDiaSerializer = new HoraDiaSerializer(ejercicioConHora.getFecha().toLocalDate(), ejercicioConHora.getFecha().toLocalTime());
            HoraDia horaDia = restTemplate.postForObject(url+"/hora-dia/buscar/", horaDiaSerializer, HoraDia.class);
            EjercicioRutina ejercicioRutina = new EjercicioRutina(null, ejercicioConHora.getRepeticiones(), rutina, ejercicio, horaDia);
            ejercicioRutinas.add(ejercicioRutina);
        }


        // Editar rutina creada
        rutina.setEjercicioRutinas(ejercicioRutinas);
        rutinaRepositorio.saveAndFlush(rutina);
        return retornarRutinaSerializer(rutina);
    }

    // UPDATE
    public RutinaSerializer editarRutina(Long id, EditarRutinaRequest request){
        Rutina rutina = buscarRutina(id);
        rutina.setTiempo(request.getTiempo());
        rutina.setEjercicioRutinas(request.getEjercicioRutinas());
        rutina.setRutinaCronogramas(request.getRutinaCronogramas());
        rutinaRepositorio.saveAndFlush(rutina);
        return retornarRutinaSerializer(rutina);
    }

    // DELETE
    public List<RutinaSerializer> eliminarRutina(Long id){
        Rutina rutina = buscarRutina(id);
        rutinaRepositorio.delete(rutina);
        return listarRutinas();
    }

    // Mapear a serializer
    public RutinaSerializer retornarRutinaSerializer(Rutina rutina){
        List<EjercicioHoraDia> ejercicios = new ArrayList<>();
        for(int i = 0; i < rutina.getEjercicioRutinas().size(); i++){
            EjercicioHoraDia ejercicioConFecha = new EjercicioHoraDia(rutina.getEjercicioRutinas().get(i).getRepeticiones(), ejercicioServicio.retornarEjercicioSerializer(rutina.getEjercicioRutinas().get(i).getEjercicio()), restTemplate.postForObject(url+"/hora-dia/serializer/", rutina.getEjercicioRutinas().get(i).getHoraDia(), HoraDiaSerializer.class));
            ejercicios.add(ejercicioConFecha);
        }
        return new RutinaSerializer(rutina.getTiempo(), ejercicios);
    }

    public Rutina buscarRutina(Long id){
        Optional<Rutina> rutina = rutinaRepositorio.findById(id);
        if(rutina.isEmpty()){
            throw new RuntimeException("Rutina no encontrada");
        }
        return rutina.get();
    }
}
