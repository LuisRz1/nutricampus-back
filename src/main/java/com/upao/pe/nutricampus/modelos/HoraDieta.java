package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hora_dieta")
@Entity
public class HoraDieta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hora_dieta")
    private Long idHoraDieta;
    @JoinColumns({
            @JoinColumn(name="id_dieta", referencedColumnName="id_dieta")
    })
    @ManyToOne
    Dieta dieta;
    @JoinColumns({
            @JoinColumn(name="id_hora_dia", referencedColumnName="id_hora_dia")
    })
    @ManyToOne
    HoraDia horaDia;
}
