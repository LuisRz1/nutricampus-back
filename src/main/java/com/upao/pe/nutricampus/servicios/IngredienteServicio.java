package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Ingrediente;
import com.upao.pe.nutricampus.repositorios.IngredienteRepositorio;
import com.upao.pe.nutricampus.serializers.ingrediente.IngredienteSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServicio {

    @Autowired private IngredienteRepositorio ingredienteRepositorio;

    // Read
    public List<IngredienteSerializer> listarIngredientes(){return ingredienteRepositorio.findAll().stream().map(this::retornarIngredienteSerializer).toList();}

    // Create
    public IngredienteSerializer crearIngrediente(IngredienteSerializer request){
        Ingrediente ingrediente = new Ingrediente(null, request.getNombre(), request.getCantidad(), request.getUnidad(), request.getCaloriasUnidad());
        return retornarIngredienteSerializer(ingredienteRepositorio.save(ingrediente));
    }

    // Update
    public IngredienteSerializer editarIngrediente(Ingrediente request){
        Optional<Ingrediente> ingrediente = ingredienteRepositorio.findById(request.getIdIngrediente());
        if(ingrediente.isEmpty()){
            throw new RuntimeException("Ingrediente no encontrado");
        }
        ingrediente.get().setNombre(request.getNombre());
        ingrediente.get().setCantidad(request.getCantidad());
        ingrediente.get().setUnidad(request.getUnidad());
        ingrediente.get().setCaloriasUnidad(request.getCaloriasUnidad());
        ingredienteRepositorio.saveAndFlush(ingrediente.get());
        return retornarIngredienteSerializer(ingrediente.get());
    }

    // Delete
    public List<IngredienteSerializer> eliminarIngrediente(Long id){
        Optional<Ingrediente> ingrediente = ingredienteRepositorio.findById(id);
        if(ingrediente.isEmpty()){
            throw new RuntimeException("Ingrediente no encontrado");
        }
        ingredienteRepositorio.delete(ingrediente.get());
        return listarIngredientes();
    }

    // Mapear a Serializer
    public IngredienteSerializer retornarIngredienteSerializer(Ingrediente ingrediente){
        return new IngredienteSerializer(ingrediente.getNombre(), ingrediente.getCantidad(), ingrediente.getUnidad(), ingrediente.getCaloriasUnidad());
    }

}
