package com.upao.pe.nutricampus.controladores;

import com.upao.pe.nutricampus.modelos.Usuario;
import com.upao.pe.nutricampus.serializers.usuario.CrearUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.EditarUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.UsuarioSerializer;
import com.upao.pe.nutricampus.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
//@CrossOrigin
public class UsuarioControlador {

    @Autowired private UsuarioServicio usuarioServicio;

    @GetMapping("/listar/")
    public List<UsuarioSerializer> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @PostMapping("/crear/")
    public UsuarioSerializer crearUsuario(@RequestBody CrearUsuarioRequest request){
        return usuarioServicio.crearUsuario(request);
    }

    @PutMapping("/editar/")
    public UsuarioSerializer editarUsuario(@RequestBody EditarUsuarioRequest request){
        return usuarioServicio.editarUsuario(request);
    }

    @DeleteMapping("/eliminar/")
    public List<UsuarioSerializer> eliminarUsuario(@RequestBody String usuario){
        return usuarioServicio.eliminarusuario(usuario);
    }
}
