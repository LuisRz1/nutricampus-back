package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.CronogramaSemanal;
import com.upao.pe.nutricampus.modelos.Rutina;
import com.upao.pe.nutricampus.repositorios.CronogramaSemanalRepositorio;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CrearCronogramaSemanalRequest;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.CronogramaSemanalSerializer;
import com.upao.pe.nutricampus.serializers.cronogramasemanal.EditarCronogramaSemanalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CronogramaSemanalServicio {

    @Autowired private CronogramaSemanalRepositorio cronogramaSemanalRepositorio;
    @Autowired private RutinaServicio rutinaServicio;

    // READ
    public List<CronogramaSemanalSerializer> listarCronogramaSemanal(){return cronogramaSemanalRepositorio.findAll().stream().map(this::retornarCronogramaSemanalSerializer).toList();}

    // CREATE
    public CronogramaSemanalSerializer crearCronogramaSemanal(CrearCronogramaSemanalRequest request){
        Rutina rutina = rutinaServicio.buscarRutina(request.getIdRutina());
        CronogramaSemanal cronogramaSemanal = new CronogramaSemanal(null, request.getFechaInicio(), request.getFechaFin(), request.getDia(), false, null, rutina);
        return retornarCronogramaSemanalSerializer(cronogramaSemanalRepositorio.save(cronogramaSemanal));
    }

    // UPDATE
    public CronogramaSemanalSerializer editarCronogramaSemanal(EditarCronogramaSemanalRequest request){
        Optional<CronogramaSemanal> cronogramaSemanal = cronogramaSemanalRepositorio.findById(request.getIdCronogramaSemanal());
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        }
        Rutina rutina = rutinaServicio.buscarRutina(request.getIdRutina());
        cronogramaSemanal.get().setFechaFin(request.getFechaInicio());
        cronogramaSemanal.get().setFechaFin(request.getFechaFin());
        cronogramaSemanal.get().setDia(request.getDia());
        cronogramaSemanal.get().setCompletado(request.isCompletado());
        cronogramaSemanal.get().setRutina(rutina);
        cronogramaSemanalRepositorio.saveAndFlush(cronogramaSemanal.get());
        return retornarCronogramaSemanalSerializer(cronogramaSemanal.get());
    }

    // DELETE
    public List<CronogramaSemanalSerializer> eliminarCronogramaSemanal(Long id){
        Optional<CronogramaSemanal> cronogramaSemanal = cronogramaSemanalRepositorio.findById(id);
        if(cronogramaSemanal.isEmpty()){
            throw new RuntimeException("No se encontro el cronograma semanal");
        }
        cronogramaSemanalRepositorio.delete(cronogramaSemanal.get());
        return listarCronogramaSemanal();
    }

    // Mapear a Serializer
    public CronogramaSemanalSerializer retornarCronogramaSemanalSerializer(CronogramaSemanal cronogramaSemanal){
        return new CronogramaSemanalSerializer(cronogramaSemanal.getFechaInicio(), cronogramaSemanal.getFechaFin(), cronogramaSemanal.getDia(), cronogramaSemanal.isCompletado(), rutinaServicio.retornarRutinaSerializer(cronogramaSemanal.getRutina()));
    }
}
