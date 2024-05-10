package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.dieta.CrearDietaRequest;
import com.upao.pe.nutricampus.serializers.dieta.DietaSerializer;
import com.upao.pe.nutricampus.serializers.dieta.EditarDietaRequest;
import com.upao.pe.nutricampus.servicios.DietaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dieta")
//@CrossOrigin
public class DietaControlador {

    @Autowired private DietaServicio dietaServicio;

    @GetMapping("/listar/")
    public List<DietaSerializer> listarDietas(){
        return dietaServicio.listarDietas();
    }

    @PostMapping("/crear/")
    public DietaSerializer crearDieta(@RequestBody CrearDietaRequest request){
        return dietaServicio.crearDieta(request);
    }

    @PutMapping("/editar/")
    public DietaSerializer editarDieta(@RequestBody EditarDietaRequest request){
        return dietaServicio.editarDieta(request);
    }

    @DeleteMapping("/eliminar/")
    public List<DietaSerializer> eliminarDieta(@RequestBody Long id){
        return dietaServicio.eliminarDieta(id);
    }
}
