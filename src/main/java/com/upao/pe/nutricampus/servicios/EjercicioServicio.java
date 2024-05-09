package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import com.upao.pe.nutricampus.repositorios.EjercicioRepositorio;
import com.upao.pe.nutricampus.serializers.ejercicio.EjercicioSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EjercicioServicio {

    @Autowired private EjercicioRepositorio ejercicioRepositorio;

    // READ
    public List<EjercicioSerializer> listarEjercicios(){return ejercicioRepositorio.findAll().stream().map(this::retornarEjercicioSerializer).toList();}

    // CREATE
    public EjercicioSerializer crearEjercicio(EjercicioSerializer request){
        Ejercicio ejercicio = new Ejercicio(null, request.getNombre(), request.getDescripcion(), request.getTiempoEjercicio(), request.getCaloriasQuemadas(), request.getVelocidadEjercicio());
        return retornarEjercicioSerializer(ejercicioRepositorio.save(ejercicio));
    }

    // UPDATE
    public EjercicioSerializer editarEjercicio(Ejercicio request){
        Optional<Ejercicio> ejercicio = ejercicioRepositorio.findById(request.getIdEjercicio());
        if(ejercicio.isEmpty()){
            throw new RuntimeException("Ejercicio no encontrado");
        }
        ejercicio.get().setNombre(request.getNombre());
        ejercicio.get().setDescripcion(request.getDescripcion());
        ejercicio.get().setTiempoEjercicio(request.getTiempoEjercicio());
        ejercicio.get().setCaloriasQuemadas(request.getCaloriasQuemadas());
        ejercicio.get().setVelocidadEjercicio(request.getVelocidadEjercicio());
        ejercicioRepositorio.saveAndFlush(ejercicio.get());
        return retornarEjercicioSerializer(ejercicio.get());
    }

    // DELETE
    public List<EjercicioSerializer> eliminarEjercicio(Long id){
        Optional<Ejercicio> ejercicio = ejercicioRepositorio.findById(id);
        if(ejercicio.isEmpty()){
            throw new RuntimeException("Ejercicio no encontrado");
        }
        ejercicioRepositorio.delete(ejercicio.get());
        return listarEjercicios();
    }

    // Mapear a Serializer
    public EjercicioSerializer retornarEjercicioSerializer(Ejercicio ejercicio){
        return new EjercicioSerializer(ejercicio.getNombre(), ejercicio.getDescripcion(), ejercicio.getTiempoEjercicio(), ejercicio.getCaloriasQuemadas(), ejercicio.getVelocidadEjercicio());
    }
}
