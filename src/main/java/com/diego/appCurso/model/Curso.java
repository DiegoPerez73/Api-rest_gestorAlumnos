package com.diego.appCurso.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "cursos")
@Entity
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nombreCurso;

    @OneToMany(mappedBy = "curso")
    List<Alumno> alumnos = new ArrayList<>();


}
