package com.upao.pe.nutricampus.serializers;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upao.pe.nutricampus.serializers.dieta.Dieta;
import com.upao.pe.nutricampus.serializers.hora_dia.HoraDia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietaComida {
    @Id
    private Long idDietaComida;
    private int raciones;
    @ManyToOne
    @JoinColumn(name = "id_dieta", nullable = false)
    @JsonIgnore
    private Dieta dieta;

    @ManyToOne
    @JoinColumn(name = "id_comida", nullable = false)
    private Comida comida;

    @ManyToOne
    @JoinColumn(name = "id_hora_dia", nullable = false)
    private HoraDia horaDia;
}
