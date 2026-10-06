package com.example.bibliotecaacessivel.model;

public class Entrada extends Movimentacao {

    public Entrada(
            double valor,
            String descricao) {

        super(valor, descricao);
    }

    @Override
    public double calcularImpacto() {

        return getValor();
    }
}