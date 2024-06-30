package com.upao.pe.nutricampus.serializers;

import com.upao.pe.nutricampus.modelos.CronogramaUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_usuario", "correo"})})
@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    @Column(name = "nombre", nullable = false)
    private String nombreCompleto;
    @Column(name = "correo", nullable = false)
    private String correo;
    @Column(name = "contra", nullable = false)
    private String contra;
    @Column(name = "foto", columnDefinition = "text")
    private String foto;
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
    @Column(name = "historial_salud", columnDefinition = "text")
    private String historialSalud;
    @Column(name = "meta")
    private char meta;
    @Column(name = "preferencias_dieteticas", nullable = false)
    private char preferenciasDieteticas;
    @Column(name = "alimentos")
    private String alimentos;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<CronogramaUsuario> cronogramaUsuario;

    public Usuario(Long idUsuario, String nombreUsuario, String nombreCompleto, String correo, String contra, String foto, int edad, double peso, double talla, char genero, char nivelActividad, String historialSalud, char meta, char preferenciasDieteticas, String alimentos, List<CronogramaUsuario> cronogramaUsuario){
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contra = contra;
        this.foto = foto;
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
        this.genero = genero;
        this.nivelActividad = nivelActividad;
        this.historialSalud = historialSalud;
        this.meta = meta;
        this.preferenciasDieteticas = preferenciasDieteticas;
        this.alimentos = alimentos;
        activo = false;
        this.cronogramaUsuario = cronogramaUsuario;
    }

}
