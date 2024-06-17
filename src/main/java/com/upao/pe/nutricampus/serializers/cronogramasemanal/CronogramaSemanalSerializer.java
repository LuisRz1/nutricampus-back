package com.upao.pe.nutricampus.serializers.cronogramasemanal;

import com.upao.pe.nutricampus.serializers.rutina.RutinaSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CronogramaSemanalSerializer {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String dia;
    private boolean completado;
    private RutinaSerializer rutina;
    private Object dieta;
}
