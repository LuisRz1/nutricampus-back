package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComidaRepositorio extends JpaRepository<Comida, Long> {
    Optional<Comida> findByNombre(String nombre);
}
