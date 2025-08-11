package com.example.catalogo_livros.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {
    private String name;
    private Integer birth_year;
    private Integer death_year;
}