package com.upao.pe.nutricampus.serializers.cronograma;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CronogramaSerializer {
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String url;
    private String backgroundColor;
    private ExtendedProps extendedProps;
}
