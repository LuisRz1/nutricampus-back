package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.modelos.cronogramausuario.CronogramaUsuario;
import com.upao.pe.nutricampus.servicios.CronogramaUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cronograma-usuario")
public class CronogramaUsuarioControlador {

    @Autowired private CronogramaUsuarioServicio cronogramaUsuarioServicio;

    @GetMapping("/listar/")
    public List<CronogramaUsuario> listarCronogramaUsuario(){
        return cronogramaUsuarioServicio.listarCronogramaUsuario();
    }

    @PostMapping("/crear/")
    public CronogramaUsuario crearCronogramaUsuario(@RequestBody CronogramaUsuario request){
        return cronogramaUsuarioServicio.crearCronogramaUsuario(request);
    }

    @DeleteMapping("/eliminar/")
    public List<CronogramaUsuario> eliminarCronogramaUsuario(@RequestBody Long id){
        return cronogramaUsuarioServicio.eliminarCronogramaUsuario(id);
    }
}
