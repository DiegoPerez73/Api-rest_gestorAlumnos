package com.diego.appCurso.controller;


import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Curso;
import com.diego.appCurso.services.AlumnoService;
import com.diego.appCurso.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;

    }

    //---Get todos los cursos---
    @GetMapping
    public List<Curso> getAll(){
        return cursoService.getAll();
    }

    //---Get por ID---
    @GetMapping("/{id}")
    public Curso getById(@PathVariable Long id){
        return cursoService.getById(id);
    }

    //---Get alumnos en el curso pasado por id---
    @GetMapping("/{id}/alumnos")
    public List<Alumno> getAlumnosPorCurso(@PathVariable Long id){
        return cursoService.getAlumnosByCursoId(id);
    }

    //---Post de curso---
    @PostMapping
    public Curso addCurso(@RequestBody Curso curso){
        return cursoService.addCurso(curso);
    }

    //Asignar alumno a un curso
    @PostMapping("/{id}/alumnos/{idAlumno}")
    public Curso newCurso(@PathVariable Long id, @PathVariable Long idAlumno){
        Curso optionalCurso = cursoService.getById(id);
        Alumno optionalAlumno = alumnoService.getById(idAlumno);
        if(optionalCurso != null && optionalAlumno!= null){
            Curso curso = optionalCurso;
            Alumno alumno = optionalAlumno;

            curso.addAlumno(alumno);
            alumno.setCurso(curso);

            return cursoService.addCurso(curso);
        }
        return cursoService.getById(id);

    }

    //--Update (PUT) curso---
    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable Long id, @RequestBody Curso curso){
        return cursoService.updateCurso(id, curso);
    }

}
