package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.serializers.usuario.AutenticacionUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.AutenticacionUsuarioResponse;
import com.upao.pe.nutricampus.serializers.usuario.CrearUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.UsuarioSerializer;
import com.upao.pe.nutricampus.servicios.AutenticacionServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("autenticacion")
//@CrossOrigin()

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


}
