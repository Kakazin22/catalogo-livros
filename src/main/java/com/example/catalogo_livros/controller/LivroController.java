package com.example.catalogo_livros.controller;

import com.example.catalogo_livros.model.Livro;
import com.example.catalogo_livros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class LivroController {
    @Autowired
    private LivroService livroService;
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n=== CATÁLOGO DE LIVROS ===");
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Listar livros cadastrados");
            System.out.println("3. Listar livros por idioma");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivros();
                case 3 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void buscarLivroPorTitulo() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scanner.nextLine();
        try {
            Livro livro = livroService.buscarPorTituloESalvar(titulo);
            System.out.println("\n Livro cadastrado: " + livro.getTitulo());
        } catch (Exception e) {
            System.out.println("\n Erro: " + e.getMessage());
        }
    }

    private void listarLivros() {
        List<Livro> livros = livroService.listarTodosLivros();
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
            return;
        }
        System.out.println("\n Livros cadastrados:");
        livros.forEach(l -> System.out.println("- " + l.getTitulo() + " (" + l.getIdioma() + ")"));
    }

    private void listarLivrosPorIdioma() {
        System.out.print("\nDigite o idioma (PT, EN, ES, FR): ");
        String idioma = scanner.nextLine().toUpperCase();
        List<Livro> livros = livroService.buscarPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro encontrado em " + idioma + ".");
            return;
        }
        System.out.println("\n Livros em " + idioma + ":");
        livros.forEach(l -> System.out.println("- " + l.getTitulo()));
    }
}