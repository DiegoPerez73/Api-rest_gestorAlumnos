package com.diego.appCurso.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "curso_id")
    Long id;

    String nombreCurso;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_curso_id", referencedColumnName = "curso_id")
    List<Alumno> alumnos;

}
