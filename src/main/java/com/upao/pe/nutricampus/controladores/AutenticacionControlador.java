package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.usuario.*;
import com.upao.pe.nutricampus.servicios.AutenticacionServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacion")

public class AutenticacionControlador {

    @Autowired private AutenticacionServicio autenticacionServicio;

    @PostMapping("/registrar/")
    public UsuarioSerializer crearUsuario(@Valid @RequestBody CrearUsuarioRequest request) {
        return autenticacionServicio.registrarUsuario(request);
    }
    @GetMapping("/token-confirmacion/{token}")
    public String activarCuenta(@PathVariable(name = "token") String token){
        return autenticacionServicio.ConfirmarCuenta(token);
    }
    @PostMapping("/iniciar-sesion/")
    public ResponseEntity<AutenticacionUsuarioResponse> iniciarSesion(@Valid @RequestBody AutenticacionUsuarioRequest request) throws Exception {
        return ResponseEntity.ok(autenticacionServicio.autenticarUsuario(request));
    }

    @PostMapping("/obtener-usuario-token/")
    public TokenResponse obtenerUsuarioPorToken(@Valid @RequestBody ObtenerUsuarioRequest request){
        return autenticacionServicio.obtenerNombreUsuario(request);
    }
}