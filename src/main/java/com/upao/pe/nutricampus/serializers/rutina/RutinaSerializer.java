package com.upao.pe.nutricampus.serializers.rutina;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class RutinaSerializer {
    private LocalTime tiempo;
    private List<Object> ejercicios;
}
