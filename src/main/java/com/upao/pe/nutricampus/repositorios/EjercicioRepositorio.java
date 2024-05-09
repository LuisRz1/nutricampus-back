package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjercicioRepositorio extends JpaRepository<Ejercicio, Long> {
}
