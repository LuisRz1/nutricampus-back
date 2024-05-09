package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_usuario", "correo"})})
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    @Column(name = "correo", nullable = false)
    private String correo;
    @Column(name = "contra", nullable = false)
    private String contra;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "peso", nullable = false)
    private double peso;
    @Column(name = "talla", nullable = false)
    private double talla;
    @Column(name = "genero", nullable = false)
    private char genero;
    @Column(name = "nivel_actividad", nullable = false)
    private char nivelActividad;
    @Column(name = "meta")
    private double meta;
    @Column(name = "velocidad_ejercicio", nullable = false)
    private char velocidadEjercicio;
}
