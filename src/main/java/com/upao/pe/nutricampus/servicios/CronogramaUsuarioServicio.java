package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.cronogramausuario.CronogramaUsuario;
import com.upao.pe.nutricampus.repositorios.CronogramaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CronogramaUsuarioServicio {

    @Autowired private CronogramaUsuarioRepositorio cronogramaUsuarioRepositorio;

    // READ
    public List<CronogramaUsuario> listarCronogramaUsuario(){return cronogramaUsuarioRepositorio.findAll();}

    // CREATE
    public CronogramaUsuario crearCronogramaUsuario(CronogramaUsuario request){
        return cronogramaUsuarioRepositorio.save(request);
    }

    // DELETE
    public List<CronogramaUsuario> eliminarCronogramaUsuario(Long id){
        Optional<CronogramaUsuario> cronogramaUsuario = cronogramaUsuarioRepositorio.findById(id);
        if(cronogramaUsuario.isEmpty()){
            throw new RuntimeException("El cronograma del usuario no fue encontrado");
        }
        cronogramaUsuarioRepositorio.delete(cronogramaUsuario.get());
        return listarCronogramaUsuario();
    }
}
