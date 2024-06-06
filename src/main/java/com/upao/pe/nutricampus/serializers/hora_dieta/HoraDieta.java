package com.upao.pe.nutricampus.serializers.hora_dieta;

import com.upao.pe.nutricampus.serializers.dieta.Dieta;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoraDieta {
    private Long idHoraDieta;
    private int hora;
    Dieta dieta;
    HoraDia horaDia;
}
