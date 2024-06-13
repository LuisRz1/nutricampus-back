package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.excepciones.RecursoExistenteExcepcion;
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
    public List<UsuarioSerializer> listarUsuarios(){return usuarioRepositorio.findAll().stream().map(this::retornarUsuarioSerializer).toList();}

    // CREATE
    public Usuario crearUsuario(CrearUsuarioRequest request){
        Usuario usuario = new Usuario(null, request.getNombreUsuario(), request.getNombreCompleto(), request.getCorreo(), request.getContra(), request.getEdad(), request.getPeso(), request.getTalla(), request.getGenero(), request.getNivelActividad(), request.getMeta(), request.getVelocidadEjercicio());
        if(usuarioRepositorio.existsUsuarioByNombreUsuario(usuario.getNombreUsuario())){
            throw new RecursoExistenteExcepcion("El usuario "+usuario.getNombreUsuario()+" existe");
        }
        if(usuarioRepositorio.existsUsuarioByCorreo(usuario.getCorreo())){
            throw new RecursoExistenteExcepcion("El email ya ha sido usado para la creación de otro usuario");
        }
        return usuarioRepositorio.save(usuario);
    }

    // UPDATE
    public UsuarioSerializer editarUsuario(EditarUsuarioRequest request){
        Usuario usuario = buscarPorNombreUsuario(request.getNombreUsuario());
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setContra(request.getContra());
        usuario.setPeso(request.getPeso());
        usuario.setTalla(request.getTalla());
        usuario.setGenero(request.getGenero());
        usuario.setNivelActividad(request.getNivelActividad());
        usuario.setMeta(request.getMeta());
        usuario.setVelocidadEjercicio(request.getVelocidadEjercicio());
        usuario.setActivo(request.isActivo());
        usuarioRepositorio.saveAndFlush(usuario);
        return retornarUsuarioSerializer(usuario);
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
        return new UsuarioSerializer(usuario.getNombreUsuario(), usuario.getNombreCompleto(), usuario.getEdad(), usuario.getPeso(), usuario.getTalla(), usuario.getGenero(), usuario.getNivelActividad(), usuario.getMeta(), usuario.getVelocidadEjercicio());
    }

    // Buscar por nombre de usuario
    public Usuario buscarPorNombreUsuario(String nombreUsuario){
        Optional<Usuario> usuario = usuarioRepositorio.findByNombreUsuario(nombreUsuario);
        if(usuario.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario.get();
    }
}
