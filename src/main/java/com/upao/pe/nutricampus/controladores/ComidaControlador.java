package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.comida.ComidaSerializer;
import com.upao.pe.nutricampus.serializers.comida.CrearComidaRequest;
import com.upao.pe.nutricampus.serializers.comida.EditarComidaRequest;
import com.upao.pe.nutricampus.servicios.ComidaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comida")
//@CrossOrigin
public class ComidaControlador {
    @Autowired private ComidaServicio comidaServicio;

    @GetMapping("/listar/")
    public List<ComidaSerializer> listarComidas(){
        return comidaServicio.listarComidas();
    }

    @PostMapping("/crear/")
    public ComidaSerializer crearComida(CrearComidaRequest request){
        return comidaServicio.crearComida(request);
    }

    @PutMapping("/editar/")
    public ComidaSerializer editarComida(EditarComidaRequest request){
        return comidaServicio.editarComida(request);
    }

    @DeleteMapping("/eliminar/")
    public List<ComidaSerializer> eliminarComida(Long id){
        return comidaServicio.eliminarComida(id);
    }
}
