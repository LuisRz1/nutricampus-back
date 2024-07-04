package com.upao.pe.nutricampus.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cronograma_usuario")
@Entity
public class CronogramaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cronograma_usuario")
    private Long idCronogramaUsuario;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_cronograma", nullable = false)
    private Cronograma cronograma;

}