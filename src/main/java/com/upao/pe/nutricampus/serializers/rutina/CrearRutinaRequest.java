package com.upao.pe.nutricampus.serializers.rutina;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CrearRutinaRequest {
    private int repeticiones;
    private LocalTime tiempo;
    private String nombreEjercicio;
}
