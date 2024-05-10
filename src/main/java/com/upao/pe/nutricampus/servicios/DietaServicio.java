package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Comida;
import com.upao.pe.nutricampus.modelos.Dieta;
import com.upao.pe.nutricampus.repositorios.DietaRepositorio;
import com.upao.pe.nutricampus.serializers.dieta.CrearDietaRequest;
import com.upao.pe.nutricampus.serializers.dieta.DietaSerializer;
import com.upao.pe.nutricampus.serializers.dieta.EditarDietaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DietaServicio {

    @Autowired DietaRepositorio dietaRepositorio;
    @Autowired ComidaServicio comidaServicio;

    // READ
    public List<DietaSerializer> listarDietas(){return dietaRepositorio.findAll().stream().map(this::retornarDietaSerializer).toList();}

    // CREATE
    public DietaSerializer crearDieta(CrearDietaRequest request){
        Comida comida = comidaServicio.buscarComida(request.getNombreComida());
        Dieta dieta = new Dieta(null, request.getRaciones(), comida);
        return retornarDietaSerializer(dietaRepositorio.save(dieta));
    }

    // UPDATE
    public DietaSerializer editarDieta(EditarDietaRequest request){
        Optional<Dieta> dieta = dietaRepositorio.findById(request.getId());
        if(dieta.isEmpty()){
            throw new RuntimeException("No se encontro la dieta");
        }
        Comida comida = comidaServicio.buscarComida(request.getNombreComida());
        dieta.get().setRaciones(request.getRaciones());
        dieta.get().setComida(comida);
        dietaRepositorio.saveAndFlush(dieta.get());
        return retornarDietaSerializer(dieta.get());
    }

    // DELETE
    public List<DietaSerializer> eliminarDieta(Long id){
        Optional<Dieta> dieta = dietaRepositorio.findById(id);
        if(dieta.isEmpty()){
            throw new RuntimeException("No se encontro la dieta");
        }
        dietaRepositorio.delete(dieta.get());
        return listarDietas();
    }

    // Mapear a Serializer
    public DietaSerializer retornarDietaSerializer(Dieta dieta){
        return new DietaSerializer(dieta.getRaciones(), comidaServicio.retornarComidaSerializer(dieta.getComida()));
    }
}
