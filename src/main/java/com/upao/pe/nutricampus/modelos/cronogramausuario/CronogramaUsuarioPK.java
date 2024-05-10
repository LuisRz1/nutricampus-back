package com.upao.pe.nutricampus.modelos.cronogramausuario;

import com.upao.pe.nutricampus.modelos.CronogramaSemanal;
import com.upao.pe.nutricampus.modelos.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
public class CronogramaUsuarioPK implements Serializable {
    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
    @JoinColumns({
            @JoinColumn(name="id_cronograma_semanal", referencedColumnName="id_cronograma_semanal")
    })
    @ManyToOne
    CronogramaSemanal cronogramaSemanal;
}
