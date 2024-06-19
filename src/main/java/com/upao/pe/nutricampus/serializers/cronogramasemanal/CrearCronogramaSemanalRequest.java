package com.upao.pe.nutricampus.serializers.cronogramasemanal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CrearCronogramaSemanalRequest {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String dia;
    private String nombreUsuario;
    private List<Long> idRutinas;
    private List<Long> idDietas;
}
