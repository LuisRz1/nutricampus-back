package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rutina")
@Entity
public class Rutina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rutina")
    private Long idRutina;
    @Column(name = "repeticiones", nullable = false)
    private int repeticiones;
    @Column(name = "tiempo")
    private LocalTime tiempo;
    @Column(name = "calorias_quemadas_totales", nullable = false)
    private double caloriasQuemadasTotales;
    @JoinColumns({
            @JoinColumn(name = "id_ejercicio", referencedColumnName = "id_ejercicio", nullable = false)
    })
    @ManyToOne
    private Ejercicio ejercicio;
}
