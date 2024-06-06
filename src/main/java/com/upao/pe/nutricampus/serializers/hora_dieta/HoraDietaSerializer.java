package com.upao.pe.nutricampus.serializers.hora_dieta;

import com.upao.pe.nutricampus.serializers.dieta.DietaSerializer;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDiaSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HoraDietaSerializer {
    private int hora;
    private DietaSerializer dieta;
    private HoraDiaSerializer momentoDia;
}
