package com.upao.pe.nutricampus.serializers.dieta;

import com.upao.pe.nutricampus.serializers.comida.ComidaSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DietaSerializer {
    private int raciones;
    private ComidaSerializer comida;
}
