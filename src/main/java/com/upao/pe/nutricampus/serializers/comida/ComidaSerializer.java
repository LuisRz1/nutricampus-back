package com.upao.pe.nutricampus.serializers.comida;

import com.upao.pe.nutricampus.serializers.ingrediente.IngredienteSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComidaSerializer {
    private String nombre;
    private String descripcion;
    private String tipo;
    private IngredienteSerializer ingrediente;
}
