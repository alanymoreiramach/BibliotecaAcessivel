package com.example.bibliotecaacessivel.model;

public abstract class Movimentacao {

    private double valor;
    private String descricao;

    public Movimentacao(
            double valor,
            String descricao) {

        this.valor = valor;
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract double calcularImpacto();
}