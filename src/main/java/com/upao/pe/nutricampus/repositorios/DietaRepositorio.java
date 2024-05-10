package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaRepositorio extends JpaRepository<Dieta, Long> {
}
