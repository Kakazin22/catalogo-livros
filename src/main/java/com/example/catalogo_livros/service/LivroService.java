package com.example.catalogo_livros.service;

import com.example.catalogo_livros.dto.LivroDTO;
import com.example.catalogo_livros.dto.ResultadoAPI;
import com.example.catalogo_livros.model.Autor;
import com.example.catalogo_livros.model.Livro;
import com.example.catalogo_livros.repository.AutorRepository;
import com.example.catalogo_livros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GutendexService gutendexService;

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma.toUpperCase());
    }

    public Livro buscarPorTituloESalvar(String titulo) {
        if (livroRepository.existsByTitulo(titulo)) {
            throw new RuntimeException("Livro já cadastrado!");
        }

        ResultadoAPI resultado = gutendexService.buscarLivrosPorTitulo(titulo);
        if (resultado.getResults().isEmpty()) {
            throw new RuntimeException("Livro não encontrado na API!");
        }

        LivroDTO livroDTO = resultado.getResults().get(0);
        Autor autor = new Autor();
        autor.setNome(livroDTO.getAuthors().get(0).getName());
        autor.setAnoNascimento(livroDTO.getAuthors().get(0).getBirth_year());
        autor.setAnoFalecimento(livroDTO.getAuthors().get(0).getDeath_year());
        autor = autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo(livroDTO.getTitle());
        livro.setIdioma(livroDTO.getLanguages().get(0).toUpperCase());
        livro.setDownloads(livroDTO.getDownload_count());
        livro.setAutor(autor);

        return livroRepository.save(livro);
    }
}