package com.example.bibliotecaacessivel.model;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private String ano;
    private String categoria;
    private String origem;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor, String ano,
                 String categoria, String origem) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.categoria = categoria;
        this.origem = origem;
        this.disponivel = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAno() {
        return ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getOrigem() {
        return origem;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}