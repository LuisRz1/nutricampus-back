package com.upao.pe.nutricampus.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cronograma")
@Entity
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cronograma")
    private Long idCronograma;
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    @Column(name = "dia", nullable = false)
    private String dia;
    @Column(name = "completado", nullable = false)
    private boolean completado;
    @OneToMany(mappedBy = "cronograma", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CronogramaUsuario> cronogramaUsuario;
}
