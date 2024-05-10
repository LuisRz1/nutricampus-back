package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.cronogramausuario.CronogramaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CronogramaUsuarioRepositorio extends JpaRepository<CronogramaUsuario, Long> {
}
