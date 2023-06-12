package com.diego.appCurso.controller;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

@Autowired
    private AlumnoService alumnoService;

//---Get todos---
    @GetMapping
    public List<Alumno> get(){ return alumnoService.getAll(); }

//---Get todos ordenados---
    @GetMapping("/sorted")
    public String getSorted(){ return alumnoService.getSorted(); }

//---Get por ID ---
    @GetMapping ("/{id}")
public Alumno getId(@PathVariable int id){ return alumnoService.getById(id);}

//---Get Avg edades---
    @GetMapping("/edadAvg")
        public String avgEdad(){
            return alumnoService.getPromedioEdades();
        }

//---Get por adeudan materias ---
    @GetMapping("/adeudan")
    public String adeudaMateria(){ return alumnoService.getAdeudaMaterias(); }

//---Get por max nota---
    @GetMapping("/maxNota")
    public String maxNota(){  return alumnoService.getNotaMasAlta(); }

//---Get por si abonaron curso---
    @GetMapping("/abono")
    public String abonaron(){ return alumnoService.getAbono(); }

//---DELETE por Id---
    @DeleteMapping ("/{id}")
    public List<Alumno> deleteById(@PathVariable int id){
        return alumnoService.deleteById(id);
    }

//---ADD alumno ---
    @PostMapping("")
    public List<Alumno> addAlumno(@RequestBody Alumno alumno){
        return alumnoService.add(alumno);
    }

//---Modificar alumno por ID---
    @PutMapping("/{id}")
    public List<Alumno> update(@PathVariable int id, @RequestBody Alumno alumno) {
            return alumnoService.update(id, alumno);
    }

}
