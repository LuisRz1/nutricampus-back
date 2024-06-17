package com.upao.pe.nutricampus.serializers.rutina;

import com.upao.pe.nutricampus.serializers.ejercicio.EjercicioSerializer;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDiaSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EjercicioHoraDia {
    private EjercicioSerializer ejercicio;
    private HoraDiaSerializer fecha;
}
