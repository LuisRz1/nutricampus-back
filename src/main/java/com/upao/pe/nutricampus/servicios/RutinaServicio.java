package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import com.upao.pe.nutricampus.modelos.Rutina;
import com.upao.pe.nutricampus.repositorios.RutinaRepositorio;
import com.upao.pe.nutricampus.serializers.rutina.CrearRutinaRequest;
import com.upao.pe.nutricampus.serializers.rutina.EditarRutinaRequest;
import com.upao.pe.nutricampus.serializers.rutina.RutinaSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RutinaServicio {

    @Autowired private RutinaRepositorio rutinaRepositorio;
    @Autowired private EjercicioServicio ejercicioServicio;

    // READ
    public List<RutinaSerializer> listarRutinas(){return rutinaRepositorio.findAll().stream().map(this::retornarRutinaSerializer).toList();}

    // CREATE
    public RutinaSerializer crearRutina(CrearRutinaRequest request){
        Ejercicio ejercicio = ejercicioServicio.buscarEjercicio(request.getNombreEjercicio());
        Rutina rutina = new Rutina(null, request.getRepeticiones(), request.getTiempo(), ejercicio);
        return retornarRutinaSerializer(rutinaRepositorio.save(rutina));
    }

    // UPDATE
    public RutinaSerializer editarRutina(EditarRutinaRequest request){
        Rutina rutina = buscarRutina(request.getIdRutina());
        Ejercicio ejercicio = ejercicioServicio.buscarEjercicio(request.getNombreEjercicio());
        rutina.setRepeticiones(request.getRepeticiones());
        rutina.setTiempo(request.getTiempo());
        rutina.setEjercicio(ejercicio);
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
        return new RutinaSerializer(rutina.getRepeticiones(), rutina.getTiempo(), ejercicioServicio.retornarEjercicioSerializer(rutina.getEjercicio()));
    }

    public Rutina buscarRutina(Long id){
        Optional<Rutina> rutina = rutinaRepositorio.findById(id);
        if(rutina.isEmpty()){
            throw new RuntimeException("Rutina no encontrada");
        }
        return rutina.get();
    }
}
