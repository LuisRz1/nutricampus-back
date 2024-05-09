package com.upao.pe.nutricampus.repositorios;

import com.upao.pe.nutricampus.modelos.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepositorio extends JpaRepository<Ingrediente, Long> {
}
