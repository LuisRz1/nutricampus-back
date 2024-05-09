package com.upao.pe.nutricampus.serializers.ejercicio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class EjercicioSerializer {
    private String nombre;
    private String descripcion;
    private LocalTime tiempoEjercicio;
    private double caloriasQuemadas;
    private char velocidadEjercicio;
}
