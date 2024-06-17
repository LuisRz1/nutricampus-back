package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.rutina.CrearRutinaRequest;
import com.upao.pe.nutricampus.serializers.rutina.EditarRutinaRequest;
import com.upao.pe.nutricampus.serializers.rutina.RutinaSerializer;
import com.upao.pe.nutricampus.servicios.RutinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rutina")
public class RutinaControlador {

    @Autowired private RutinaServicio rutinaServicio;

    @GetMapping("/listar/")
    public List<RutinaSerializer> listarRutinas(){
        return rutinaServicio.listarRutinas();
    }

    @PostMapping("/crear/")
    public RutinaSerializer crearRutina(@RequestBody CrearRutinaRequest request){
        return rutinaServicio.crearRutina(request);
    }

    @PutMapping("/editar/{id}")
    public RutinaSerializer editarRutina(@PathVariable Long id, @RequestBody EditarRutinaRequest request){
        return rutinaServicio.editarRutina(id, request);
    }

    @DeleteMapping("/eliminar/{id}")
    public List<RutinaSerializer> eliminarRutina(@PathVariable Long id){
        return rutinaServicio.eliminarRutina(id);
    }
}
