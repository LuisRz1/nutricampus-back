package com.upao.pe.nutricampus.serializers.rutina;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CrearRutinaRequest {
    private int repeticiones;
    private LocalTime tiempo;
    private List<EjercicioConHoraCrear> ejercicios;
}
