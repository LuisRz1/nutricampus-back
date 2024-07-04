package com.upao.pe.nutricampus.controladores;



import com.upao.pe.nutricampus.serializers.usuario.EditarUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.UsuarioSerializer;
import com.upao.pe.nutricampus.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioControlador {

    @Autowired private UsuarioServicio usuarioServicio;

    @GetMapping("/listar/")
    public List<UsuarioSerializer> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @PutMapping("/editar/{usuario}")
    public UsuarioSerializer editarUsuario(@PathVariable() String usuario, @RequestBody EditarUsuarioRequest request){
        return usuarioServicio.editarUsuario(usuario, request);
    }

    @DeleteMapping("/eliminar/{usuario}")
    public List<UsuarioSerializer> eliminarUsuario(@PathVariable() String usuario){
        return usuarioServicio.eliminarusuario(usuario);
    }

    @GetMapping("/buscar-por-usuario/{usuario}")
    public UsuarioSerializer buscarPorUsuario(@PathVariable() String usuario){
        return usuarioServicio.retornarUsuarioSerializer(usuarioServicio.buscarPorNombreUsuario(usuario));
    }
}
