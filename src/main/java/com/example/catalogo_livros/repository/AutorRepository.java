package com.example.catalogo_livros.repository;

import com.example.catalogo_livros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE (:ano BETWEEN a.anoNascimento AND a.anoFalecimento) OR (a.anoNascimento <= :ano AND a.anoFalecimento IS NULL)")
    List<Autor> findAutoresVivosNoAno(@Param("ano") Integer ano);
}