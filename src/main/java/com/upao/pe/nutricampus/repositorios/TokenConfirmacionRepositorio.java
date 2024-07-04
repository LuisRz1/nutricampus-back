package com.upao.pe.nutricampus.repositorios;



import com.upao.pe.nutricampus.modelos.TokenConfirmacion;
import com.upao.pe.nutricampus.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenConfirmacionRepositorio extends JpaRepository<TokenConfirmacion, Long> {
    boolean existsTokenConfirmacionByToken(String token);
    TokenConfirmacion findByToken(String token);
    TokenConfirmacion saveAndFlush(TokenConfirmacion confirmationToken);
    TokenConfirmacion findByUsuario(Usuario usuario);
}
