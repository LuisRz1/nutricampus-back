package com.upao.pe.nutricampus.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime fechaInicio;
    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;
    @Column(name = "nombre_evento", nullable = false)
    private String nombreEvento;
    @Column(name = "url", columnDefinition = "text")
    private String url;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "color_fondo", nullable = false)
    private String colorFondo;
    @OneToMany(mappedBy = "cronograma", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CronogramaUsuario> cronogramaUsuario;
}
