package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.Usuario;
import com.upao.pe.nutricampus.repositorios.UsuarioRepositorio;
import com.upao.pe.nutricampus.serializers.usuario.CrearUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.EditarUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.UsuarioSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired private UsuarioRepositorio usuarioRepositorio;

    // READ
    public List<UsuarioSerializer> listarUsuarios(){return usuarioRepositorio.findAll().stream().map((it) -> retornarUsuarioSerializer(it)).toList();}

    // CREATE
    public UsuarioSerializer crearUsuario(CrearUsuarioRequest request){
        Usuario usuario = new Usuario(null, request.getNombreUsuario(), request.getCorreo(), request.getContra(), request.getEdad(), request.getPeso(), request.getTalla(), request.getGenero(), request.getNivelActividad(), request.getMeta(), request.getVelocidadEjercicio());
        return retornarUsuarioSerializer(usuarioRepositorio.save(usuario));
    }

    // UPDATE
    public UsuarioSerializer editarUsuario(EditarUsuarioRequest request){
        Optional<Usuario> usuario = usuarioRepositorio.findByNombreUsuario(request.getNombreUsuario());
        if(usuario.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        usuario.get().setContra(request.getContra());
        usuario.get().setPeso(request.getPeso());
        usuario.get().setTalla(request.getTalla());
        usuario.get().setGenero(request.getGenero());
        usuario.get().setNivelActividad(request.getNivelActividad());
        usuario.get().setMeta(request.getMeta());
        usuario.get().setVelocidadEjercicio(request.getVelocidadEjercicio());
        usuarioRepositorio.saveAndFlush(usuario.get());
        return retornarUsuarioSerializer(usuario.get());
    }

    // DELETE
    public List<UsuarioSerializer> eliminarusuario(String nombreUsuario){
        Optional<Usuario> usuario = usuarioRepositorio.findByNombreUsuario(nombreUsuario);
        if(usuario.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepositorio.delete(usuario.get());
        return listarUsuarios();
    }

    // Mapear a Serializer
    public UsuarioSerializer retornarUsuarioSerializer(Usuario usuario){
        return new UsuarioSerializer(usuario.getNombreUsuario(), usuario.getEdad(), usuario.getPeso(), usuario.getTalla(), usuario.getGenero(), usuario.getNivelActividad(), usuario.getMeta(), usuario.getVelocidadEjercicio());
    }

}
