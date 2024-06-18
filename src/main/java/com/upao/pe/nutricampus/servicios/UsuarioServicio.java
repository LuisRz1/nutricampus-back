package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.excepciones.RecursoExistenteExcepcion;
import com.upao.pe.nutricampus.modelos.Usuario;
import com.upao.pe.nutricampus.repositorios.UsuarioRepositorio;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CronogramaSemanalSerializer;
import com.upao.pe.nutricampus.serializers.usuario.CrearUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.EditarUsuarioRequest;
import com.upao.pe.nutricampus.serializers.usuario.UsuarioSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired private UsuarioRepositorio usuarioRepositorio;

    // READ
    public List<UsuarioSerializer> listarUsuarios(){return usuarioRepositorio.findAll().stream().map(this::retornarUsuarioSerializer).toList();}

    // CREATE
    public Usuario crearUsuario(CrearUsuarioRequest request){
        Usuario usuario = new Usuario(null, request.getNombreUsuario(), request.getNombreCompleto(), request.getCorreo(), request.getContra(), request.getFoto(), request.getEdad(), request.getPeso(), request.getTalla(), request.getGenero(), request.getNivelActividad(), request.getHistorialSalud(), request.getMeta(), request.getPreferenciasDieteticas(), request.getAlimentos(), null);
        if(usuarioRepositorio.existsUsuarioByNombreUsuario(usuario.getNombreUsuario())){
            throw new RecursoExistenteExcepcion("El usuario "+usuario.getNombreUsuario()+" existe");
        }
        if(usuarioRepositorio.existsUsuarioByCorreo(usuario.getCorreo())){
            throw new RecursoExistenteExcepcion("El email ya ha sido usado para la creación de otro usuario");
        }
        return usuarioRepositorio.save(usuario);
    }

    // UPDATE
    public UsuarioSerializer editarUsuario(String nombreUsuario, EditarUsuarioRequest request){
        Usuario usuario = buscarPorNombreUsuario(nombreUsuario);
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setFoto(request.getFoto());
        usuario.setEdad(request.getEdad());
        usuario.setPeso(request.getPeso());
        usuario.setTalla(request.getTalla());
        usuario.setGenero(request.getGenero());
        usuario.setNivelActividad(request.getNivelActividad());
        usuario.setHistorialSalud(request.getHistorialSalud());
        usuario.setMeta(request.getMeta());
        usuario.setPreferenciasDieteticas(request.getPreferenciasDieteticas());
        usuario.setAlimentos(request.getAlimentos());
        usuario.setActivo(request.isActivo());
        actualizarCambios(usuario);
        return retornarUsuarioSerializer(usuario);
    }

    // DELETE
    public List<UsuarioSerializer> eliminarusuario(String nombreUsuario){
        Usuario usuario = buscarPorNombreUsuario(nombreUsuario);
        usuarioRepositorio.delete(usuario);
        return listarUsuarios();
    }

    // Mapear a Serializer
    public UsuarioSerializer retornarUsuarioSerializer(Usuario usuario){
        List<CronogramaSemanalSerializer> cronogramas = new ArrayList<>();
        if(usuario.getCronogramaUsuario() != null){
            for(int i = 0; i < usuario.getCronogramaUsuario().size(); i++){
                cronogramas.add(new CronogramaSemanalSerializer(usuario.getCronogramaUsuario().get(i).getCronogramaSemanal().getFechaInicio(), usuario.getCronogramaUsuario().get(i).getCronogramaSemanal().getFechaFin(), usuario.getCronogramaUsuario().get(i).getCronogramaSemanal().getDia(), usuario.getCronogramaUsuario().get(i).getCronogramaSemanal().isCompletado()));
            }
        }
        return new UsuarioSerializer(usuario.getNombreUsuario(), usuario.getNombreCompleto(), usuario.getFoto(), usuario.getEdad(), usuario.getPeso(), usuario.getTalla(), usuario.getGenero(), usuario.getNivelActividad(), usuario.getHistorialSalud(), usuario.getMeta(), usuario.getPreferenciasDieteticas(), usuario.getAlimentos(), cronogramas);
    }

    // Buscar por nombre de usuario
    public Usuario buscarPorNombreUsuario(String nombreUsuario){
        Optional<Usuario> usuario = usuarioRepositorio.findByNombreUsuario(nombreUsuario);
        if(usuario.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuario.get();
    }

    public void actualizarCambios(Usuario usuario) {
        usuarioRepositorio.saveAndFlush(usuario);
    }
}
