package com.upao.pe.nutricampus.serializers.cronogramasemanal;


import com.upao.pe.nutricampus.serializers.dieta.DietaSerializer;
import com.upao.pe.nutricampus.serializers.rutina.RutinaSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CronogramaSemanalSerializer {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String dia;
    private boolean completado;
    private List<RutinaSerializer> rutinas;
    private List<DietaSerializer> dietas;
}
