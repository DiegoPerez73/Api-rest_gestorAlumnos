package com.diego.appCurso.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Table(name = "alumnos")
@Entity
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private Boolean adeudaMateria;
    private int nota;
    private Boolean abono;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id") //Referencia al Long id de la entity Curso
    private Curso curso;

}


