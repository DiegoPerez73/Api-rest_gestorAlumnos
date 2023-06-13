package com.diego.appCurso.controller;


import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.model.Curso;
import com.diego.appCurso.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cursos")
@RestController
public class CursoController {
    @Autowired
    CursoService cursoService;

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

    //--Update (PUT) curso---
    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable Long id, @RequestBody Curso curso){
        return cursoService.updateCurso(id, curso);
    }

}
