package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.*;
import com.upao.pe.nutricampus.repositorios.CronogramaRepositorio;
import com.upao.pe.nutricampus.serializers.cronograma.CrearCronogramaRequest;
import com.upao.pe.nutricampus.serializers.cronograma.CronogramaSerializer;
import com.upao.pe.nutricampus.serializers.cronograma.EditarCronogramaRequest;
import com.upao.pe.nutricampus.serializers.cronograma.ExtendedProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CronogramaServicio {

    @Autowired private CronogramaRepositorio cronogramaRepositorio;
    @Autowired private UsuarioServicio usuarioServicio;

    // READ
    public List<CronogramaSerializer> listarCronogramas(){return cronogramaRepositorio.findAll().stream().map(this::retornarCronogramaSerializer).toList();}

    // CREATE
    public CronogramaSerializer crearCronograma(CrearCronogramaRequest request){
        Cronograma cronograma = new Cronograma(null, request.getFechaInicio(), request.getFechaFin(), request.getNombreEvento(), request.getUrl(), request.getNombre(), request.getColorFondo(), null);
        cronogramaRepositorio.save(cronograma);

        // Creando relacion con el usuario
        Usuario usuario = usuarioServicio.buscarPorNombreUsuario(request.getNombreUsuario());
        CronogramaUsuario cronogramaUsuario = new CronogramaUsuario(null, usuario, cronograma);
        usuario.getCronogramaUsuario().add(cronogramaUsuario);
        usuarioServicio.actualizarCambios(usuario);
        return retornarCronogramaSerializer(cronograma);
    }

    // UPDATE
    public CronogramaSerializer editarCronograma(Long id, EditarCronogramaRequest request){
        Cronograma cronograma = buscarCronograma(id);
        cronograma.setFechaFin(request.getFechaInicio());
        cronograma.setFechaFin(request.getFechaFin());
        cronograma.setNombreEvento(request.getNombreEvento());
        cronograma.setUrl(request.getUrl());
        cronograma.setNombre(request.getNombre());
        cronogramaRepositorio.saveAndFlush(cronograma);
        return retornarCronogramaSerializer(cronograma);
    }

    // DELETE
    public List<CronogramaSerializer> eliminarCronograma(Long id){
        Optional<Cronograma> cronogramaSemanal = cronogramaRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el evento del cronograma");
        }
        cronogramaRepositorio.delete(cronogramaSemanal.get());
        return listarCronogramas();
    }

    // Mapear a Serializer
    public CronogramaSerializer retornarCronogramaSerializer(Cronograma cronograma){
        ExtendedProps extendedProps = new ExtendedProps(cronograma.getNombre());
        return new CronogramaSerializer(cronograma.getIdCronograma().intValue(), cronograma.getFechaInicio(), cronograma.getFechaFin(), cronograma.getNombreEvento(), cronograma.getUrl(), cronograma.getColorFondo(), extendedProps);
    }

    // Buscar crongorama
    public Cronograma buscarCronograma(Long id){
        Optional<Cronograma> cronogramaSemanal = cronogramaRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        } return cronogramaSemanal.get();
    }

    // Listar cronogramas por usuario
    public List<CronogramaSerializer> listarCronogramasPorUsuario(String nombreUsuario){
        Usuario usuario = usuarioServicio.buscarPorNombreUsuario(nombreUsuario);
        return usuarioServicio.retornarUsuarioSerializer(usuario).getCronogramaSemanal();
    }
}