package com.upao.pe.nutricampus.serializers.dieta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditarDietaRequest {
    private Long id;
    private int raciones;
    private String nombreComida;
}
