package com.upao.pe.nutricampus.serializers.rutina;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EjercicioConHoraCrear {
    private String ejercicio;
    private LocalDateTime fecha;
}
