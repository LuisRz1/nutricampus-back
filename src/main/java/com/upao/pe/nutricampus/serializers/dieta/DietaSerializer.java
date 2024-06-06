package com.upao.pe.nutricampus.serializers.dieta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DietaSerializer {
    private int raciones;
    private Object comida;
}
