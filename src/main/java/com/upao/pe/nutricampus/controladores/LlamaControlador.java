package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.LlamaResponse;
import com.upao.pe.nutricampus.servicios.LlamaIAServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ia")
public class LlamaControlador {

    @Autowired LlamaIAServicio llamaIAServicio;

    @PostMapping("/generar/")
    public ResponseEntity<LlamaResponse> generarMensaje(@RequestBody String prompt) {
        final LlamaResponse iaResponse = llamaIAServicio.generarMensaje(prompt);
        return ResponseEntity.status(HttpStatus.OK).body(iaResponse);
    }

    @GetMapping("/generar/broma/{tema}")
    public ResponseEntity<LlamaResponse> generarBroma(@PathVariable String tema) {
        final LlamaResponse iaResponse = llamaIAServicio.generarBroma(tema);
        return ResponseEntity.status(HttpStatus.OK).body(iaResponse);
    }
}
