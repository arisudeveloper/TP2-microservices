package br.edu.infnet.biblioteca.servicelivro.controller;

import br.edu.infnet.biblioteca.servicelivro.model.Livro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final List<Livro> livros = new ArrayList<>();
    private Long proximoId = 1L;

    public LivroController() {
        livros.add(new Livro(proximoId++, "É assim que acaba", "Colleen Hoover"));
        livros.add(new Livro(proximoId++, "Cidade de Papel", "John Green"));
    }

    @GetMapping
    public List<Livro> listarTodos() {
        return livros;
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {
        return livros.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Livro criar(@RequestBody Livro livro) {
        livro.setId(proximoId++);
        livros.add(livro);
        return livro;
    }
}