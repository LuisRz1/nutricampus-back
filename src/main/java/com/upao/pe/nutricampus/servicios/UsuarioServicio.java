package com.upao.pe.nutricampus.servicios;



import com.upao.pe.nutricampus.excepciones.RecursoExistenteExcepcion;
import com.upao.pe.nutricampus.modelos.CronogramaUsuario;
import com.upao.pe.nutricampus.modelos.Usuario;
import com.upao.pe.nutricampus.repositorios.UsuarioRepositorio;
import com.upao.pe.nutricampus.serializers.cronograma.CronogramaSerializer;
import com.upao.pe.nutricampus.serializers.cronograma.ExtendedProps;
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
    @Autowired private TokenServicio tokenServicio;

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
        tokenServicio.eliminarToken(usuario);
        usuarioRepositorio.delete(usuario);
        return listarUsuarios();
    }

    // Mapear a Serializer
    public UsuarioSerializer retornarUsuarioSerializer(Usuario usuario){
        List<CronogramaSerializer> cronogramas = new ArrayList<>();
        if(usuario.getCronogramaUsuario() != null){
            for(CronogramaUsuario cronogramaUsuario: usuario.getCronogramaUsuario()){
                ExtendedProps extendedProps = new ExtendedProps(cronogramaUsuario.getCronograma().getNombre());
                CronogramaSerializer cronograma = new CronogramaSerializer(cronogramaUsuario.getCronograma().getIdCronograma().intValue(), cronogramaUsuario.getCronograma().getFechaInicio(), cronogramaUsuario.getCronograma().getFechaFin(), cronogramaUsuario.getCronograma().getNombreEvento(), cronogramaUsuario.getCronograma().getUrl(), cronogramaUsuario.getCronograma().getColorFondo(), extendedProps);
                cronogramas.add(cronograma);
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