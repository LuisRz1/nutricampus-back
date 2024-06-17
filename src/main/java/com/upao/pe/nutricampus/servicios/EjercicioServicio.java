package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import com.upao.pe.nutricampus.repositorios.EjercicioRepositorio;
import com.upao.pe.nutricampus.serializers.ejercicio.EditarEjercicioRequest;
import com.upao.pe.nutricampus.serializers.ejercicio.EjercicioSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EjercicioServicio {

    @Autowired private EjercicioRepositorio ejercicioRepositorio;

    // READ
    public List<EjercicioSerializer> listarEjercicios(){return ejercicioRepositorio.findAll().stream().map(this::retornarEjercicioSerializer).toList();}

    // CREATE
    public EjercicioSerializer crearEjercicio(EjercicioSerializer request){
        Ejercicio ejercicio = new Ejercicio(null, request.getNombre(), request.getDescripcion(), request.getTiempoEjercicio(), request.getCaloriasQuemadas(), request.getVelocidadEjercicio(), new ArrayList<>());
        return retornarEjercicioSerializer(ejercicioRepositorio.save(ejercicio));
    }

    // UPDATE
    public EjercicioSerializer editarEjercicio(String nombre, EditarEjercicioRequest request){
        Ejercicio ejercicio = buscarEjercicio(nombre);
        ejercicio.setNombre(request.getNombre());
        ejercicio.setDescripcion(request.getDescripcion());
        ejercicio.setTiempoEjercicio(request.getTiempoEjercicio());
        ejercicio.setCaloriasQuemadas(request.getCaloriasQuemadas());
        ejercicio.setVelocidadEjercicio(request.getVelocidadEjercicio());
        ejercicio.setEjercicioRutinas(request.getEjercicioRutinas());
        ejercicioRepositorio.saveAndFlush(ejercicio);
        return retornarEjercicioSerializer(ejercicio);
    }

    // DELETE
    public List<EjercicioSerializer> eliminarEjercicio(String nombre){
        Ejercicio ejercicio = buscarEjercicio(nombre);
        ejercicioRepositorio.delete(ejercicio);
        return listarEjercicios();
    }

    // Mapear a Serializer
    public EjercicioSerializer retornarEjercicioSerializer(Ejercicio ejercicio){
        return new EjercicioSerializer(ejercicio.getNombre(), ejercicio.getDescripcion(), ejercicio.getTiempoEjercicio(), ejercicio.getCaloriasQuemadas(), ejercicio.getVelocidadEjercicio());
    }

    public Ejercicio buscarEjercicio(String nombre){
        Optional<Ejercicio> ejercicio = ejercicioRepositorio.findByNombre(nombre);
        if(ejercicio.isEmpty()){
            throw new RuntimeException("Ejercicio no encontrado");
        }
        return ejercicio.get();
    }
}
