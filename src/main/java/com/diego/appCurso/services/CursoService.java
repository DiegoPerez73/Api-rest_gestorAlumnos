package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Curso;
import com.diego.appCurso.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;


    //---Mostrar los cursos---
    public List<Curso> getAll(){
        return cursoRepository.findAll();
    }

    //---Mostrar curso por id---
    public Curso getById(Long id){
        return cursoRepository.findById(id).orElse(null);
    }

    //---Mostrar alumnos por curso---
    public List<Alumno> getAlumnosByCursoId(Long id){

        Curso cursoId = cursoRepository.findById(id).orElse(null); //Busco el curso por id. Esto devuelve un Optional, por eso lo guardo asi.

        if(cursoId != null){            //Si el curso con ese id existe-->
            return cursoId.getAlumnos();
        }
        return Collections.emptyList();
    }

    //---Añadir curso---
    public Curso addCurso(Curso curso){
       return cursoRepository.save(curso);
    }

    //---Modificar curso---
    public Curso updateCurso(Long id, Curso curso){

        Curso cursoAnterior = cursoRepository.findById(id).orElse(null);

        if(cursoAnterior != null){
            cursoAnterior.setId(curso.getId());
            cursoAnterior.setNombreCurso(curso.getNombreCurso());
            cursoAnterior.setAlumnos(curso.getAlumnos());
            return cursoRepository.save(cursoAnterior);
        }
        throw new RuntimeException("No se encontro ningun alumno con el id "+id);
    }


}
