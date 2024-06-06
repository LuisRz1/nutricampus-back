package com.upao.pe.nutricampus.serializers.hora_dia;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoraDia {
    private Long idHoraDia;
    private String hora;
}
