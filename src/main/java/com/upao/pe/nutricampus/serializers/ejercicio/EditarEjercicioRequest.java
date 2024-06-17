package com.upao.pe.nutricampus.serializers.ejercicio;

import com.upao.pe.nutricampus.modelos.EjercicioRutina;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Data
public class EditarEjercicioRequest {
    private String nombre;
    private String descripcion;
    private LocalTime tiempoEjercicio;
    private double caloriasQuemadas;
    private char velocidadEjercicio;
    private List<EjercicioRutina> ejercicioRutinas;
}