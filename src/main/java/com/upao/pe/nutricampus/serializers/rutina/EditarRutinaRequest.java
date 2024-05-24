package com.upao.pe.nutricampus.serializers.rutina;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class EditarRutinaRequest {
    private Long idRutina;
    private int repeticiones;
    private LocalTime tiempo;
    private String nombreEjercicio;
}
