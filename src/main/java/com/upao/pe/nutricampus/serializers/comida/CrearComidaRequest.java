package com.upao.pe.nutricampus.serializers.comida;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrearComidaRequest {
    private String nombre;
    private String descripcion;
    private String tipo;
    private String nombreIngrediente;
}
