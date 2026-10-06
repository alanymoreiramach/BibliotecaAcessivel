package com.example.bibliotecaacessivel.service;

import com.example.bibliotecaacessivel.model.Livro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {

    private static Biblioteca instancia;

    private final List<Livro> livros;
    private int proximoId = 1;

    private Biblioteca() {
        livros = new ArrayList<>();
    }

    public static Biblioteca getInstancia() {

        if (instancia == null) {
            instancia = new Biblioteca();
        }

        return instancia;
    }

    public void adicionarLivro(
            String titulo,
            String autor,
            String ano,
            String categoria,
            String origem) {

        Livro livro = new Livro(
                proximoId,
                titulo,
                autor,
                ano,
                categoria,
                origem
        );

        livros.add(livro);
        proximoId++;
    }

    public List<Livro> listarLivros() {

        List<Livro> listaOrdenada = new ArrayList<>(livros);

        listaOrdenada.sort(
                Comparator.comparing(
                        Livro::getTitulo,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return listaOrdenada;
    }

    public Livro buscarPorId(int id) {

        for (Livro livro : livros) {

            if (livro.getId() == id) {
                return livro;
            }
        }

        return null;
    }

    public void removerLivro(int id) {

        Livro livro = buscarPorId(id);

        if (livro != null) {
            livros.remove(livro);
        }
    }

    public boolean realizarDoacao(int id) {

        Livro livro = buscarPorId(id);

        if (livro != null && livro.isDisponivel()) {

            livro.setDisponivel(false);
            return true;
        }

        return false;
    }
}