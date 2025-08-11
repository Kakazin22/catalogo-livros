package com.example.catalogo_livros.service;

import com.example.catalogo_livros.model.Autor;
import com.example.catalogo_livros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosNoAno(Integer ano) {
        return autorRepository.findAutoresVivosNoAno(ano);
    }
}