package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class AlumnoService {

    private List<Alumno> alumnos = new ArrayList<>();

    public AlumnoService(){
        loadAlumnos();
    }

    public void loadAlumnos(){
        Alumno a1 = new Alumno("Natalia", "Romeo", 30, false, 9, true);
        Alumno a2 = new Alumno("Diego", "Vazquez", 25, true, 9, false);
        Alumno a3 = new Alumno("Roberto", "Corbera", 18, false, 8, true);
        Alumno a4 = new Alumno("Gaston", "Gonzalez", 22, true, 9, false);
        Alumno a5 = new Alumno("Sandra", "Gomez", 31, false, 6, true);
        Alumno a6 = new Alumno("Agustin", "Perez", 29, true, 7, false);
        alumnos.add(a1);
        alumnos.add(a2);
        alumnos.add(a3);
        alumnos.add(a4);
        alumnos.add(a5);
        alumnos.add(a6);
    }

    //---Mostrar Informacion completa (desordenada)---
    public List<Alumno> getAll() {
        return alumnos;
    }

    //---Mostrar informacion ordenada de alumnos por apellido ---

        //Esto quedo raro, se que hay una mejor forma de hacerlo seguro, pero no la encuentro, y me quemé jaja.
    public List<String> getSorted() {

        alumnos.sort((alumno1, alumno2) -> alumno1.getLastName().compareTo(alumno2.getLastName()));

        List<String> respuesta = new ArrayList<>();

        alumnos.forEach(alumno -> respuesta.add(alumno.getName() + " " + alumno.getLastName() + " -" + alumno.getAge() + "años. ") );

        return respuesta;
    }

    //---Mostrar el promedio de edad---

    public String  getPromedioEdades() {
        int totalEdades = 0;

        for (Alumno alumno : alumnos) {
            totalEdades += alumno.getAge();
        }

        double promedio = (double) totalEdades / alumnos.size();

        return "\nEl promedio de las edades de los alumnos es: " + promedio;
    }



    //---Mostrar el numero de alumnos que adeudan materias---

    public Set<Alumno> getAdeudaMaterias(){

        return alumnos.stream().filter(Alumno::isAdeudaMateria).collect(Collectors.toSet());

    }


    //---Mostrar alumno con nota mas alta---

                //Devuelvo la lista de alumnos con nota mas alta. Pero tengo un problema generalizado, NO SE como poder devolver en un endpoint,
                //Un String mio personalizado, + instancias de Alumnos, por ejemplo aca me gustaria poder devolver
                // un String: Hay "x" cantidad de alumnos con "X" nota mas alta y son: (ACA DEVOLVER INSTANCIAS ENTERAS por ej)...
    public List<Alumno> getNotaMasAlta(){

        alumnos.sort(Comparator.comparing(Alumno::getLastName).reversed());

        int notaMasAlta = alumnos.get(0).getNota();

        List<Alumno> notaMasAltaList = alumnos.stream().filter(alumno -> alumno.getNota() == notaMasAlta).toList();

        return notaMasAltaList;
    }



    //---Mostrar si algun alumno no abono la matricula---


            //Mismo problema que arriba. NO se como devolver los nombres de cada alumno que no abono. Devuelvo lista de no abonados...
    public List<Alumno> getAbono(){

        return alumnos.stream().filter(alumno -> !alumno.isAbono()).toList();

//        int cantNoAbono = noAbonaron.size();
//
//        if(cantNoAbono>1){
//            return cantNoAbono + " alumnos no abonaron la matricula.";
//        } else if (cantNoAbono == 0) {
//           return "Todos los alumnos abonaron!";
//        } else return noAbonaron.get(0).getName() + " " + noAbonaron.get(0).getLastName() + " no abonó la matrícula.";

    }


    //---Retornar alumno por ID---
    public Alumno getAlumnoId(int id){


        return alumnos.stream().filter(alumno -> alumno.getId() == id).findFirst().orElse(null);
    }


    //---Eliminar un alumno---
    public List<Alumno> eliminarAlumnoPorId(int id){

        alumnos.removeIf(alumno -> alumno.getId() == id);

        return alumnos;
    }


    //---Añadir alumnos---

    //Aca tengo una duda, yo por el formato que elegi de asignar ID de alumno, no puedo hacer que aca se me agregue buscando "huecos" en ids, y asignar ese id faltante ahi.
    //Que podria hacer, generar un metodo que recorra los id, y complete en el faltante? O es mejor asignar al constructor de Alumno otro metodo para que complete los ids?
    public List<Alumno> add(Alumno alumno) {
        alumnos.add(alumno);
        return alumnos;
    }

    //---Update de alumno---

    public List<Alumno> updateAlumno(int id, Alumno nuevoAlumno) throws Exception {

        Alumno alumnoAnterior = getAlumnoId(id);

        if(Objects.isNull(alumnoAnterior)){
            throw new Exception("Alumno no encontrado");
        }

        alumnos.remove(alumnoAnterior);
        nuevoAlumno.setId(id);
        alumnos.add(nuevoAlumno);
        return alumnos;
    }

}
