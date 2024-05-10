package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cronograma_semanal")
@Entity
public class CronogramaSemanal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cronograma_semanal")
    private Long idCronogramaSemanal;
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    @Column(name = "dia")
    private String dia;
    @Column(name = "completado")
    private boolean completado;
    @JoinColumns({
            @JoinColumn(name="id_hora_dieta", referencedColumnName="id_hora_dieta")
    })
    @ManyToOne
    HoraDieta horaDieta;
    @JoinColumns({
            @JoinColumn(name="id_rutina", referencedColumnName="id_rutina")
    })
    @ManyToOne
    Rutina rutina;
}
