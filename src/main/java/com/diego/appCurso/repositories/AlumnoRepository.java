package com.diego.appCurso.repositories;


import com.diego.appCurso.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    Alumno findByDni(String dni);

}
