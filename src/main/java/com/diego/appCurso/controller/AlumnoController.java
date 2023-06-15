package com.diego.appCurso.controller;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.services.AlumnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> getAll(@RequestParam(required = false) Boolean adeudaMateria){
        if (!Objects.isNull(adeudaMateria)){
            return alumnoService.getAllByAdeuda(adeudaMateria);
        }else return alumnoService.getAll();
    }

//---Get por ID ---
    @GetMapping ("/{id}")
    public Alumno getId(@PathVariable Long id){ return alumnoService.getById(id);}

//---Get todos ordenados---
    @GetMapping("/sorted")
    public List<Alumno> getSorted(){ return alumnoService.getSorted(); }

//---Get Avg edades---
    @GetMapping("/edadAvg")
        public double avgEdad(){
            return alumnoService.getPromedioEdades();
        }

//---Get por adeudan materias ---
    @GetMapping("/adeudan")
    public List<Alumno> adeudaMateria(){ return alumnoService.getAdeudaMaterias(); }

//---Get por max nota---
    @GetMapping("/maxNota")
    public Alumno maxNota(){  return alumnoService.getNotaMasAlta(); }

//---Get por si abonaron curso---
    @GetMapping("/abono")
    public List<Alumno> abonaron(){ return alumnoService.getAbono(); }

//---ADD alumno ---
    @PostMapping
    public Alumno addAlumno(@RequestBody Alumno alumno){
        return alumnoService.add(alumno);
    }

//---Modificar alumno por ID---
    @PutMapping("/{id}")
    public Alumno update(@PathVariable Long id, @RequestBody Alumno alumno) {
            return alumnoService.update(id, alumno);
    }

//---DELETE por Id---
    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id){
        alumnoService.deleteById(id);
    }


}
