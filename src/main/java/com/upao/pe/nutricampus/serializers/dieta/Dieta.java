package com.upao.pe.nutricampus.serializers.dieta;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dieta {
    private Long idDieta;
    private int raciones;
    private Long idComida;
}
