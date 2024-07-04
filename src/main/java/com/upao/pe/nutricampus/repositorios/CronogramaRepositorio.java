package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CronogramaRepositorio extends JpaRepository<Cronograma, Long> {
}
