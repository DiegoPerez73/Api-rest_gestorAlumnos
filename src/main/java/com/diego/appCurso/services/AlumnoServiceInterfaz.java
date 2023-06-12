package com.diego.appCurso.services;

import com.diego.appCurso.model.Alumno;

import java.util.List;

public interface AlumnoServiceInterfaz {
    List<Alumno> getAll();

    String getSorted();

    String getPromedioEdades();

    String getAdeudaMaterias();

    String getNotaMasAlta();

    String getAbono();

    Alumno getById(int id);

    List<Alumno> deleteById(int id);

    List<Alumno> add(Alumno alumno);

    List<Alumno> update(int id, Alumno nuevoAlumno) throws Exception;
}
