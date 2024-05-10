package com.upao.pe.nutricampus.modelos.cronogramausuario;

import com.upao.pe.nutricampus.modelos.CronogramaSemanal;
import com.upao.pe.nutricampus.modelos.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cronograma_usuario")
@Entity
@IdClass(value = CronogramaUsuarioPK.class)
public class CronogramaUsuario {
    @Id
    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
    @Id
    @JoinColumns({
            @JoinColumn(name="id_cronograma_semanal", referencedColumnName="id_cronograma_semanal")
    })
    @ManyToOne
    CronogramaSemanal cronogramaSemanal;

}
