package com.upao.pe.nutricampus.serializers.cronograma;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CrearCronogramaRequest {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String nombreEvento;
    private String nombre;
    private String url;
    private String nombreUsuario;
    private String colorFondo;
}
