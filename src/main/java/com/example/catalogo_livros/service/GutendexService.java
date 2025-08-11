package com.example.catalogo_livros.service;

import com.example.catalogo_livros.dto.ResultadoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GutendexService {
    private static final String GUTENDEX_API_URL = "https://gutendex.com/books/?search=";

    @Autowired
    private RestTemplate restTemplate;

    public ResultadoAPI buscarLivrosPorTitulo(String titulo) {
        String url = GUTENDEX_API_URL + titulo.replace(" ", "%20");
        return restTemplate.getForObject(url, ResultadoAPI.class);
    }
}