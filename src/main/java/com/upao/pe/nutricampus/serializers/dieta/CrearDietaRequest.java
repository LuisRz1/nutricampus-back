package com.upao.pe.nutricampus.serializers.dieta;

import com.upao.pe.nutricampus.serializers.comida.ComidaSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CrearDietaRequest {
    private int raciones;
    private String nombreComida;
}
