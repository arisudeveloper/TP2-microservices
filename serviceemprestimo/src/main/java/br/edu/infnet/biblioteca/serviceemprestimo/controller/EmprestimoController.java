package br.edu.infnet.biblioteca.serviceemprestimo.controller;

import br.edu.infnet.biblioteca.serviceemprestimo.model.Emprestimo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private Long proximoId = 1L;
    private final RestTemplate restTemplate = new RestTemplate();

    public EmprestimoController() {
        emprestimos.add(new Emprestimo(proximoId++, 1L, "Alice Silva"));
    }

    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimos;
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable Long id) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Object criar(@RequestBody Emprestimo emprestimo) {
        String urlLivro = "http://container-livro:8080/livros/" + emprestimo.getLivroId();
        try {
            Object livro = restTemplate.getForObject(urlLivro, Object.class);

            if (livro == null) {
                return "Erro: Livro com ID " + emprestimo.getLivroId() + " não foi encontrado no servicelivro.";
            }
            emprestimo.setId(proximoId++);
            emprestimos.add(emprestimo);
            return emprestimo;

        } catch (Exception e) {
            return "Erro ao comunicar com o servicelivro: " + e.getMessage();
        }
    }
}