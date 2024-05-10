package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Comida;
import com.upao.pe.nutricampus.modelos.Ingrediente;
import com.upao.pe.nutricampus.repositorios.ComidaRepositorio;
import com.upao.pe.nutricampus.serializers.comida.ComidaSerializer;
import com.upao.pe.nutricampus.serializers.comida.CrearComidaRequest;
import com.upao.pe.nutricampus.serializers.comida.EditarComidaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComidaServicio {

    @Autowired private ComidaRepositorio comidaRepositorio;
    @Autowired private IngredienteServicio ingredienteServicio;

    // READ
    public List<ComidaSerializer> listarComidas(){return comidaRepositorio.findAll().stream().map(this::retornarComidaSerializer).toList();}

    // CREATE
    public ComidaSerializer crearComida(CrearComidaRequest request){
        Ingrediente ingrediente = ingredienteServicio.buscarIngrediente(request.getNombreIngrediente());
        Comida comida = new Comida(null, request.getNombre(), request.getDescripcion(), request.getTipo(), ingrediente);
        return retornarComidaSerializer(comidaRepositorio.save(comida));
    }

    // UPDATE
    public ComidaSerializer editarComida(EditarComidaRequest request){
        Optional<Comida> comida = comidaRepositorio.findById(request.getId());
        if(comida.isEmpty()){
            throw new RuntimeException("No se encontro la comida");
        }
        Ingrediente ingrediente = ingredienteServicio.buscarIngrediente(request.getNombreEjercicio());
        comida.get().setNombre(request.getNombre());
        comida.get().setDescripcion(request.getDescripcion());
        comida.get().setTipo(request.getTipo());
        comida.get().setIngrediente(ingrediente);
        comidaRepositorio.saveAndFlush(comida.get());
        return retornarComidaSerializer(comida.get());
    }

    // DELETE
    public List<ComidaSerializer> eliminarComida(Long id){
        Optional<Comida> comida = comidaRepositorio.findById(id);
        if(comida.isEmpty()){
            throw new RuntimeException("No se encontro la comida");
        }
        comidaRepositorio.delete(comida.get());
        return listarComidas();
    }

    // Mapear a Serializer
    public ComidaSerializer retornarComidaSerializer(Comida comida){
        return new ComidaSerializer(comida.getNombre(), comida.getDescripcion(), comida.getTipo(), ingredienteServicio.retornarIngredienteSerializer(comida.getIngrediente()));
    }

    public Comida buscarComida(String nombre){
        Optional<Comida> comida = comidaRepositorio.findByNombre(nombre);
        if(comida.isEmpty()){
            throw new RuntimeException("No se encontro la comida");
        }
        return comida.get();
    }
}
