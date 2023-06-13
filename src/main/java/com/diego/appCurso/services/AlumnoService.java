package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;
import com.diego.appCurso.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;



@Service
public class AlumnoService{

    @Autowired
    AlumnoRepository alumnoRepository;

    //---Mostrar Informacion completa (desordenada)---

    public List<Alumno> getAll() {
        return alumnoRepository.findAll();
    }

    public List<Alumno> getAllByAdeuda(Boolean adeudaMateria){
        return alumnoRepository.findAllByAdeudaMateria(adeudaMateria);
    }


    //---Retornar alumno por ID---

    public Alumno getById(Long id){
        return alumnoRepository.findById(id).orElse(null);
    }

    //---Eliminar un alumno---
    public void deleteById(Long id){
        alumnoRepository.deleteById(id);
    }

    //---Mostrar informacion ordenada de alumnos por apellido ---

    public List<Alumno> getSorted() {
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        return listaAlumnos.stream().sorted(Comparator.comparing(Alumno::getApellido)).toList();
    }

    //---Mostrar el promedio de edad---

    public double getPromedioEdades() {

        List<Alumno> listaAlumnos = alumnoRepository.findAll();

        int totalEdades = 0;

        for (Alumno alumno : listaAlumnos) {
            totalEdades += alumno.getEdad();
        }

        return (double) totalEdades /listaAlumnos.size();
    }

    //---Mostrar el numero de alumnos que adeudan materias---


    public List<Alumno> getAdeudaMaterias(){
       List<Alumno> listaAlumnos = alumnoRepository.findAll();

       return listaAlumnos.stream().filter(alumno -> alumno.getAdeudaMateria()).toList();
    }

    //---Mostrar alumno con nota mas alta---

    public Alumno getNotaMasAlta(){

        List<Alumno> listaAlumnos = alumnoRepository.findAll();

        listaAlumnos.sort(Comparator.comparing(Alumno::getNota).reversed());

        return listaAlumnos.get(0);

    }

    //---Mostrar si algun alumno no abono la matricula---

    public List<Alumno> getAbono(){

        List<Alumno> listaAlumnos = alumnoRepository.findAll();

        return listaAlumnos.stream().filter(Alumno::getAbono).toList();
    }

    //---Añadir alumnos---

    public Alumno add(Alumno alumno) {
        return alumnoRepository.save(alumno);

    }

    //---Update de alumno---
    public Alumno update(Long id, Alumno nuevoAlumno) {

        Alumno alumnoAnterior = alumnoRepository.findById(id).orElse(null);
        if(alumnoAnterior != null){
            alumnoAnterior.setId(id);
            alumnoAnterior.setNombre(nuevoAlumno.getNombre());
            alumnoAnterior.setApellido(nuevoAlumno.getApellido());
            alumnoAnterior.setEdad(nuevoAlumno.getEdad());
            alumnoAnterior.setAbono(nuevoAlumno.getAbono());
            alumnoAnterior.setDni(nuevoAlumno.getDni());
            alumnoAnterior.setAdeudaMateria(nuevoAlumno.getAdeudaMateria());
            alumnoAnterior.setNota(nuevoAlumno.getNota());

            return alumnoRepository.save(alumnoAnterior);
        }else {
            throw new RuntimeException("No se encontro ningun alumno con el id "+id);
        }
    }

}
