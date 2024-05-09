package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.modelos.Ingrediente;
import com.upao.pe.nutricampus.serializers.ingrediente.IngredienteSerializer;
import com.upao.pe.nutricampus.servicios.IngredienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingrediente")
//@CrossOrigin()
public class IngredienteControlador {

    @Autowired private IngredienteServicio ingredienteServicio;

    @GetMapping("/listar/")
    public List<IngredienteSerializer> listarIngredientes(){
        return ingredienteServicio.listarIngredientes();
    }

    @PostMapping("/crear/")
    public IngredienteSerializer crearIngrediente(@RequestBody IngredienteSerializer request){
        return ingredienteServicio.crearIngrediente(request);
    }

    @PutMapping("/editar/")
    public IngredienteSerializer editarIngrediente(@RequestBody Ingrediente request){
        return ingredienteServicio.editarIngrediente(request);
    }

    @DeleteMapping("/eliminar/")
    public List<IngredienteSerializer> eliminarIngrediente(@RequestBody Long id){
        return ingredienteServicio.eliminarIngrediente(id);
    }
}
