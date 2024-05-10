package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingrediente", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ingrediente")
    private Long idIngrediente;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "cantidad", nullable = false)
    private double cantidad;
    @Column(name = "unidad", nullable = false)
    private String unidad;
    @Column(name = "calorias_unidad", nullable = false)
    private double caloriasUnidad;
}
