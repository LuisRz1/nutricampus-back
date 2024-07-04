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
    public List<CronogramaSerializer> listarCronogramaSemanal(){
        return cronogramaServicio.listarCronogramaSemanal();
    }

    @PostMapping("/crear/")
    public CronogramaSerializer crearCronogramaSemanal(@RequestBody CrearCronogramaRequest request){
        return cronogramaServicio.crearCronogramaSemanal(request);
    }

    @PutMapping("/editar/{id}")
    public CronogramaSerializer editarCronogramaSemanal(@PathVariable Long id, @RequestBody EditarCronogramaRequest request){
        return cronogramaServicio.editarCronogramaSemanal(id, request);
    }

    @DeleteMapping("/eliminar/{id}")
    public List<CronogramaSerializer> eliminarCronogramaSemanal(@PathVariable Long id){
        return cronogramaServicio.eliminarCronogramaSemanal(id);
    }
}
