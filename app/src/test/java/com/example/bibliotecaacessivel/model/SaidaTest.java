package com.example.bibliotecaacessivel.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SaidaTest {

    @Test
    public void deveRetornarImpactoNegativo() {
        Saida saida = new Saida(30.00, "Compra de livros");

        assertEquals(-30.00, saida.calcularImpacto(), 0.001);
    }

    @Test
    public void deveManterValorDaSaida() {
        Saida saida = new Saida(45.00, "Material de limpeza");

        assertEquals(45.00, saida.getValor(), 0.001);
    }

    @Test
    public void deveManterDescricaoDaSaida() {
        Saida saida = new Saida(20.00, "Manutenção");

        assertEquals("Manutenção", saida.getDescricao());
    }
}
