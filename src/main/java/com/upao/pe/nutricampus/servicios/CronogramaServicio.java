package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.*;
import com.upao.pe.nutricampus.repositorios.CronogramaRepositorio;
import com.upao.pe.nutricampus.serializers.cronograma.CrearCronogramaRequest;
import com.upao.pe.nutricampus.serializers.cronograma.CronogramaSerializer;
import com.upao.pe.nutricampus.serializers.cronograma.EditarCronogramaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CronogramaServicio {

    @Autowired private CronogramaRepositorio cronogramaRepositorio;
    @Autowired private UsuarioServicio usuarioServicio;

    // READ
    public List<CronogramaSerializer> listarCronogramaSemanal(){return cronogramaRepositorio.findAll().stream().map(this::retornarCronogramaSemanalSerializer).toList();}

    // CREATE
    public CronogramaSerializer crearCronogramaSemanal(CrearCronogramaRequest request){
        Cronograma cronograma = new Cronograma(null, request.getFechaInicio(), request.getFechaFin(), request.getDia(), false, null);
        cronogramaRepositorio.save(cronograma);

        // Creando relacion con el usuario
        Usuario usuario = usuarioServicio.buscarPorNombreUsuario(request.getNombreUsuario());
        CronogramaUsuario cronogramaUsuario = new CronogramaUsuario(null, usuario, cronograma);
        usuario.getCronogramaUsuario().add(cronogramaUsuario);
        usuarioServicio.actualizarCambios(usuario);
        return retornarCronogramaSemanalSerializer(cronograma);
    }

    // UPDATE
    public CronogramaSerializer editarCronogramaSemanal(Long id, EditarCronogramaRequest request){
        Cronograma cronograma = buscarCronogramaSemanal(id);

        cronograma.setFechaFin(request.getFechaInicio());
        cronograma.setFechaFin(request.getFechaFin());
        cronograma.setDia(request.getDia());
        cronograma.setCompletado(request.isCompletado());
        cronogramaRepositorio.saveAndFlush(cronograma);
        return retornarCronogramaSemanalSerializer(cronograma);
    }

    // DELETE
    public List<CronogramaSerializer> eliminarCronogramaSemanal(Long id){
        Optional<Cronograma> cronogramaSemanal = cronogramaRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        }
        cronogramaRepositorio.delete(cronogramaSemanal.get());
        return listarCronogramaSemanal();
    }

    // Mapear a Serializer
    public CronogramaSerializer retornarCronogramaSemanalSerializer(Cronograma cronograma){
        return new CronogramaSerializer(cronograma.getFechaInicio(), cronograma.getFechaFin(), cronograma.getDia(), cronograma.isCompletado());
    }

    // Buscar crongorama
    public Cronograma buscarCronogramaSemanal(Long id){
        Optional<Cronograma> cronogramaSemanal = cronogramaRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        } return cronogramaSemanal.get();
    }
}