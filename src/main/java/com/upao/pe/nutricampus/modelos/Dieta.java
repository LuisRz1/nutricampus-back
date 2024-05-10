package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dieta")
@Entity
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dieta")
    private Long idDieta;
    @Column(name = "raciones", nullable = false)
    private int raciones;
    @JoinColumns({
            @JoinColumn(name = "id_comida", referencedColumnName = "id_comida", nullable = false)
    })
    @ManyToOne
    private Comida comida;
}
