package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.HoraDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraDietaRepositorio extends JpaRepository<HoraDieta, Long> {
}
