package com.upao.pe.nutricampus.serializers.comida;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EditarComidaRequest {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String nombreEjercicio;
}
