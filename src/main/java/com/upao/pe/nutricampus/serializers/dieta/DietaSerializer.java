package com.upao.pe.nutricampus.serializers.dieta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DietaSerializer {
    private int raciones;
    private List<Object> comidas;
}
