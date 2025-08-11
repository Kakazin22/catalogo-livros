package com.example.catalogo_livros.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ResultadoAPI {
    private List<LivroDTO> results;
}