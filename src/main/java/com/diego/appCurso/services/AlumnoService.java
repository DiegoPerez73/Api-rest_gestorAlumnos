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

    //Aca tengo una duda, yo por el formato que elegi de asignar ID de alumno, no puedo hacer que aca se me agregue buscando "huecos" en ids, y asignar ese id faltante ahi.
    //Que podria hacer, generar un metodo que recorra los id, y complete en el faltante? O es mejor asignar al constructor de Alumno otro metodo para que complete los ids?

    @Override
    public List<Alumno> add(Alumno alumno) {
        alumnos.add(alumno);
        return alumnos;
    }

    //---Update de alumno---
@Override
    public List<Alumno> update(int id, Alumno nuevoAlumno) {

        Alumno alumnoAnterior = alumnos.stream().filter(alumno -> alumno.getId()== id).findFirst().orElse(null);
        alumnos.remove(alumnoAnterior);
        nuevoAlumno.setId(id);
        alumnos.add(nuevoAlumno);
        return alumnos;
    }

}
