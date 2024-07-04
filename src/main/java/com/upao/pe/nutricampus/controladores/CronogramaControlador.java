package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.cronograma.CrearCronogramaRequest;
import com.upao.pe.nutricampus.serializers.cronograma.CronogramaSerializer;
import com.upao.pe.nutricampus.serializers.cronograma.EditarCronogramaRequest;
import com.upao.pe.nutricampus.servicios.CronogramaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cronograma")
public class CronogramaControlador {

    @Autowired private CronogramaServicio cronogramaServicio;

    @GetMapping("/listar/")
    public List<CronogramaSerializer> listarCronograma(){
        return cronogramaServicio.listarCronogramas();
    }

    @PostMapping("/crear/")
    public CronogramaSerializer crearCronograma(@RequestBody CrearCronogramaRequest request){
        return cronogramaServicio.crearCronograma(request);
    }

    @PutMapping("/editar/{id}")
    public CronogramaSerializer editarCronograma(@PathVariable Long id, @RequestBody EditarCronogramaRequest request){
        return cronogramaServicio.editarCronograma(id, request);
    }

    @DeleteMapping("/eliminar/{id}")
    public List<CronogramaSerializer> eliminarCronograma(@PathVariable Long id){
        return cronogramaServicio.eliminarCronograma(id);
    }

    @GetMapping("/listar-por-usuario/{nombre}")
    public List<CronogramaSerializer> listarCronogramaPorUsuario(@PathVariable() String nombre){
        return cronogramaServicio.listarCronogramasPorUsuario(nombre);
    }
}
