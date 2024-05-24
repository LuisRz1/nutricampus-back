package com.upao.pe.nutricampus.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre_usuario", "correo"})})
@Entity
public class Usuario implements UserDetails {
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
    @Column(name = "activo", nullable = false)
    private boolean activo;

    public Usuario(Long idUsuario, String nombreUsuario, String correo, String contra, int edad, double peso, double talla, char genero, char nivelActividad, double meta, char velocidadEjercicio){
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contra = contra;
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
        this.genero = genero;
        this.nivelActividad = nivelActividad;
        this.meta = meta;
        this.velocidadEjercicio = velocidadEjercicio;
        activo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return contra;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
