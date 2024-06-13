package com.upao.pe.nutricampus.serializers.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ObtenerUsuarioRequest {
    private String token;
    private String relleno; // Ignoren este atributo, GRACIAS!!!!
}
