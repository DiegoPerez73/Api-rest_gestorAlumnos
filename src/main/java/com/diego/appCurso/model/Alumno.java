package com.diego.appCurso.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @NotNull
    @Size
    @Column(length = 8)
    private String dni;
    private int edad;
    private Boolean adeudaMateria;
    private int nota;
    private Boolean abono;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

}


