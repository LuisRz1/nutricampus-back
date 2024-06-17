package com.upao.pe.nutricampus.serializers;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDia;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DietaComida {
    @Id
    private Long idDietaComida;
    private Long idDieta;
    private Long idComida;
    @ManyToOne
    @JoinColumn(name = "id_hora_dia", nullable = false)
    @JsonBackReference
    private HoraDia horaDia;
}
