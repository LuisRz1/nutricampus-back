package com.upao.pe.nutricampus.servicios;

import com.upao.pe.nutricampus.modelos.HoraDieta;
import com.upao.pe.nutricampus.repositorios.HoraDietaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HoraDietaServicio {

    @Autowired private HoraDietaRepositorio horaDietaRepositorio;

    // READ
    public List<HoraDieta> listarHoraDietas(){return horaDietaRepositorio.findAll();}

    // CREATE
    public HoraDieta crearHoraDieta(HoraDieta horaDieta){return horaDietaRepositorio.save(horaDieta);}

    // DELETE
    public List<HoraDieta> eliminarHoraDieta(Long id){
        Optional<HoraDieta> horaDieta = horaDietaRepositorio.findById(id);
        if(horaDieta.isEmpty()){
            throw new RuntimeException("No se encuentra la hora de la dieta");
        }
        horaDietaRepositorio.delete(horaDieta.get());
        return listarHoraDietas();
    }

}
