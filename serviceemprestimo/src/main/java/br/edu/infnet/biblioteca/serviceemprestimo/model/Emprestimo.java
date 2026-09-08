package br.edu.infnet.biblioteca.serviceemprestimo.model;

public class Emprestimo {
    private Long id;
    private Long livroId;
    private String nomeLeitor;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Long livroId, String nomeLeitor) {
        this.id = id;
        this.livroId = livroId;
        this.nomeLeitor = nomeLeitor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getNomeLeitor() {
        return nomeLeitor;
    }

    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }
}