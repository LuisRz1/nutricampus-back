package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hora_dia", uniqueConstraints = {@UniqueConstraint(columnNames = {"hora"})})
@Entity
public class HoraDia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_hora_dia")
    private Long idHoraDia;
    @Column(name = "hora", nullable = false)
    private String hora;
}
