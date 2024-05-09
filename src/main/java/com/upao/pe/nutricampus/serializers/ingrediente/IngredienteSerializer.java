package com.upao.pe.nutricampus.serializers.ingrediente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredienteSerializer {
    private String nombre;
    private double cantidad;
    private String unidad;
    private double caloriasUnidad;
}
