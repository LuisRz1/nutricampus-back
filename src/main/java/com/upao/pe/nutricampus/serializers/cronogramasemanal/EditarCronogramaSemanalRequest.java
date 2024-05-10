package com.upao.pe.nutricampus.serializers.cronogramasemanal;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EditarCronogramaSemanalRequest {
    private Long idCronogramaSemanal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String dia;
    private boolean completado;
    private Long idRutina;
}
