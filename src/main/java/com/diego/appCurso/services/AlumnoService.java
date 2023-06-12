package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;
import org.springframework.stereotype.Service;
import java.util.*;



@Service
public class AlumnoService implements AlumnoServiceInterfaz {

    private List<Alumno> alumnos = new ArrayList<>();




    //---Mostrar Informacion completa (desordenada)---
    @Override
    public List<Alumno> getAll() {
        return alumnos;
    }

    //---Mostrar informacion ordenada de alumnos por apellido ---
    @Override
    public String getSorted() {

        alumnos.sort((alumno1, alumno2) -> alumno1.getLastName().compareTo(alumno2.getLastName()));

        List<String> ordenados = alumnos.stream().map(a->a.getName() + " " + a.getLastName() + "\t " + a.getAge()).toList();

        return String.format(String.join("\n",ordenados));
    }

    //---Mostrar el promedio de edad---
    @Override
    public String  getPromedioEdades() {
        int totalEdades = 0;

        for (Alumno alumno : alumnos) {
            totalEdades += alumno.getAge();
        }
        double promedio = (double) totalEdades / alumnos.size();
        return "\nEl promedio de las edades de los alumnos es: " + promedio;
    }

    //---Mostrar el numero de alumnos que adeudan materias---
@Override
    public String getAdeudaMaterias(){

        List<String> alumnosAdeudan = alumnos.stream().filter(Alumno::isAdeudaMateria)
                                            .map(a-> a.getName() + " "+a.getLastName())
                                            .toList();

        if(alumnosAdeudan.size() == 0) return "Ningun alumno adeuda materias";
        if(alumnosAdeudan.size() == 1) return alumnosAdeudan.toString();
        else {
            return String.format("%d alumnos adeudan materias.\nSon los alumnos:\n%s ",alumnosAdeudan.size(), String.join("\n",alumnosAdeudan) );
        }
}

    //---Mostrar alumno con nota mas alta---
    @Override
    public String getNotaMasAlta(){

        alumnos.sort(Comparator.comparing(Alumno::getNota).reversed());

        int notaMasAlta = alumnos.get(0).getNota();
        long cantidadAlumnosConNota = alumnos.stream().filter(alumno -> alumno.getNota() == notaMasAlta).count();

        List<String> alumnosNotaMasAlta = alumnos.stream().filter(alumno -> alumno.getNota() == notaMasAlta)
                                        .map(alumno -> alumno.getName() + " " + alumno.getLastName())
                                        .toList();

        if(cantidadAlumnosConNota == 1){
            return String.format("La nota mas alta es %d\nAlumno: %s ",notaMasAlta, String.join("",alumnosNotaMasAlta));
        } else {
            return String.format("La nota mas alta es %d\nHay %d alumnos con esa nota\nAlumnos:\n%s",notaMasAlta,cantidadAlumnosConNota,String.join("\n",alumnosNotaMasAlta));
        }
    }

    //---Mostrar si algun alumno no abono la matricula---
    @Override
    public String getAbono(){

        List<String> noAbonaron = alumnos.stream().filter(alumno -> !alumno.isAbono()).map(a->a.getName() + " " + a.getLastName()).toList();

        if(noAbonaron.size() > 1 && noAbonaron.size()<alumnos.size()) return String.format("%d alumnos no abonaron la matrícula.\nSon los alumnos:\n%s",noAbonaron.size(),String.join("\n",noAbonaron));
        if(noAbonaron.size() == 1) return String.format("%s no abonó la matrícula ",String.join("",noAbonaron));
        if (noAbonaron.size() == alumnos.size()) return "Ningun alumno abonó la matricula";
        else return "Todos los alumnos abonaron";
    }

    //---Retornar alumno por ID---
    @Override
    public Alumno getById(int id){
        return alumnos.stream().filter(alumno -> alumno.getId() == id).findFirst().orElse(null);
    }

    //---Eliminar un alumno---
    @Override
    public List<Alumno> deleteById(int id){
        alumnos.removeIf(alumno -> alumno.getId() == id);
        return alumnos;
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
