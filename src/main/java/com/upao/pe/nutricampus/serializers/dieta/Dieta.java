package com.upao.pe.nutricampus.serializers.dieta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upao.pe.nutricampus.modelos.DietaCronograma;
import com.upao.pe.nutricampus.serializers.DietaComida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dieta {
    @Id
    @Column(name = "id_dieta")
    private Long idDieta;
    private int raciones;
    @JsonIgnore
    private Long idComida;
    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DietaCronograma> dietaCronogramas;
    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL)
    private List<DietaComida> dietaComidas;
}
