package com.example.bibliotecaacessivel.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntradaTest {

    @Test
    public void deveRetornarImpactoPositivo() {
        Entrada entrada = new Entrada(100.00, "Doação em dinheiro");

        assertEquals(100.00, entrada.calcularImpacto(), 0.001);
    }

    @Test
    public void deveManterValorDaEntrada() {
        Entrada entrada = new Entrada(50.00, "Contribuição");

        assertEquals(50.00, entrada.getValor(), 0.001);
    }

    @Test
    public void deveManterDescricaoDaEntrada() {
        Entrada entrada = new Entrada(75.00, "Doação voluntária");

        assertEquals("Doação voluntária", entrada.getDescricao());
    }
}
