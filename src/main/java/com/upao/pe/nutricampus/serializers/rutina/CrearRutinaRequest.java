package com.upao.pe.nutricampus.serializers.rutina;

import com.upao.pe.nutricampus.serializers.ejercicio.EjercicioSerializer;
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
