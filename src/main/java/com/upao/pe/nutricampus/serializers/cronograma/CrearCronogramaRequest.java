package com.upao.pe.nutricampus.serializers.cronograma;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CrearCronogramaRequest {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String dia;
    private String nombreUsuario;
}
