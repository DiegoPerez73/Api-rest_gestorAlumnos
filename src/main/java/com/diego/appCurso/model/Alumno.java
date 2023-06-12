package com.diego.appCurso.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Alumno {
    private static int contadorId = 0;
    private int id;
    private String name;
    private String lastName;
    private int age;
    private boolean adeudaMateria;
    private int nota;
    private boolean abono;

    public Alumno(String name, String lastName, int age, boolean adeudaMateria, int nota, boolean abono) {
        this.id = ++contadorId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.adeudaMateria = adeudaMateria;
        this.nota = nota;
        this.abono = abono;
    }



}


