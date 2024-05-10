package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import com.upao.pe.nutricampus.modelos.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutinaRepositorio extends JpaRepository<Rutina, Long> {
}
