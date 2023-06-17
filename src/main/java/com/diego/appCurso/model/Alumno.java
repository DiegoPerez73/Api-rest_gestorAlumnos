package com.diego.appCurso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;

    @NotNull
    @Size(max = 8)
    @Column(length = 8)
    private String dni;

    @JsonIgnore //Lo uso porque no quiero que devuelva el password en los GET
    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.ALUMNO;

    private Integer nota;
    private Boolean adeudaMateria;
    private Boolean abono;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;


    //---Aca implemento los metodos de UserDetails---

    //---Este va a traerme el enum de roles donde asigno autoridades---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    //---Estos son los importantes, me van a traer el userName y Password---
    @JsonIgnore
    @Override
    public String getUsername() {       //Aca debo poner de UserName, el atributo que voy a usar de usuario.
        return dni;
    }
    @JsonIgnore
    @Override
    public String getPassword() {       //Este no sale solo en las implementaciones de UserDetails (ATENTO A AGREGARLO)
        return password;
    }



    //---Estos los pongo todos en true---
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}


