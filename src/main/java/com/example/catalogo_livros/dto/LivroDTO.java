package com.example.catalogo_livros.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class LivroDTO {
    private String title;
    private List<AutorDTO> authors;
    private List<String> languages;
    private Integer download_count;
}