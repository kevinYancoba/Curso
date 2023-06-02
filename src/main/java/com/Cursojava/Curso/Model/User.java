package com.Cursojava.Curso.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "usuarios")
@ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter @Getter
    private long id;

    @Setter @Getter @Column(name = "nombre")
    private String nombre;

    @Setter @Getter @Column(name = "apellido")
    private String apellido;

    @Setter @Getter  @Column(name = "telefono")
    private String telefono;

    @Setter @Getter  @Column(name = "email")
    private String email;

    @Setter @Getter @Column(name = "pasword")
    private String pasword;


}
