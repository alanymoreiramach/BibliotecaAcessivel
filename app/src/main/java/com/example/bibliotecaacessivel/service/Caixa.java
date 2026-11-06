package com.example.bibliotecaacessivel.service;

import com.example.bibliotecaacessivel.model.Movimentacao;

import java.util.ArrayList;
import java.util.List;

public class Caixa {

    private static Caixa instancia;

    private final List<Movimentacao> movimentacoes;

    private Caixa() {

        movimentacoes = new ArrayList<>();
    }

    public static Caixa getInstancia() {

        if (instancia == null) {
            instancia = new Caixa();
        }

        return instancia;
    }

    public void registrarMovimentacao(
            Movimentacao movimentacao) {

        movimentacoes.add(movimentacao);
    }

    public double calcularSaldo() {

        double saldo = 0;

        for (Movimentacao movimentacao : movimentacoes) {

            saldo += movimentacao.calcularImpacto();
        }

        return saldo;
    }

    public List<Movimentacao> listarMovimentacoes() {

        return new ArrayList<>(movimentacoes);
    }

    void limparMovimentacoes() {
        movimentacoes.clear();
    }

}