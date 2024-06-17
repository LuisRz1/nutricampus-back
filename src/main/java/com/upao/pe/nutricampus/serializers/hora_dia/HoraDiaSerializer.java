package com.upao.pe.nutricampus.serializers.hora_dia;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class HoraDiaSerializer {
    private LocalDate fecha;
    private LocalTime hora;
}
