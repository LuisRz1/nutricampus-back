package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import com.upao.pe.nutricampus.serializers.ejercicio.EjercicioSerializer;
import com.upao.pe.nutricampus.servicios.EjercicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ejercicio")
public class EjercicioControlador {

    @Autowired private EjercicioServicio ejercicioServicio;

    @GetMapping("/listar/")
    public List<EjercicioSerializer> listarEjercicios(){
        return ejercicioServicio.listarEjercicios();
    }

    @PostMapping("/crear/")
    public EjercicioSerializer crearEjercicio(@RequestBody EjercicioSerializer request){
        return ejercicioServicio.crearEjercicio(request);
    }

    @PutMapping("/editar/")
    public EjercicioSerializer editarEjercicio(@RequestBody Ejercicio request){
        return ejercicioServicio.editarEjercicio(request);
    }

    @DeleteMapping("/eliminar/")
    public List<EjercicioSerializer> eliminarEjercicio(@RequestBody Long id){
        return ejercicioServicio.eliminarEjercicio(id);
    }
}
