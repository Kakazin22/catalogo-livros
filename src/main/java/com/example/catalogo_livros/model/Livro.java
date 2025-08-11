package com.example.catalogo_livros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}