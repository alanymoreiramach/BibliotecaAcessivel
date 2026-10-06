package com.example.bibliotecaacessivel.model;

public class Saida extends Movimentacao {

    public Saida(
            double valor,
            String descricao) {

        super(valor, descricao);
    }

    @Override
    public double calcularImpacto() {

        return -getValor();
    }
}