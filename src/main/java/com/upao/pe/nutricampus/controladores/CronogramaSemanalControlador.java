package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.cronogramasemanal.CrearCronogramaSemanalRequest;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CronogramaSemanalSerializer;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.EditarCronogramaSemanalRequest;
import com.upao.pe.nutricampus.servicios.CronogramaSemanalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cronograma-semanal")
public class CronogramaSemanalControlador {

    @Autowired private CronogramaSemanalServicio cronogramaSemanalServicio;

    @GetMapping("/listar/")
    public List<CronogramaSemanalSerializer> listarCronogramaSemanal(){
        return cronogramaSemanalServicio.listarCronogramaSemanal();
    }

    @PostMapping("/crear/")
    public CronogramaSemanalSerializer crearCronogramaSemanal(@RequestBody CrearCronogramaSemanalRequest request){
        return cronogramaSemanalServicio.crearCronogramaSemanal(request);
    }

    @PutMapping("/editar/")
    public CronogramaSemanalSerializer editarCronogramaSemanal(@RequestBody EditarCronogramaSemanalRequest request){
        return cronogramaSemanalServicio.editarCronogramaSemanal(request);
    }

    @DeleteMapping("/eliminar/")
    public List<CronogramaSemanalSerializer> eliminarCronogramaSemanal(@RequestBody Long id){
        return cronogramaSemanalServicio.eliminarCronogramaSemanal(id);
    }
}
