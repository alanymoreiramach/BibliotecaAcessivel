package com.example.bibliotecaacessivel.service;

import com.example.bibliotecaacessivel.model.Entrada;
import com.example.bibliotecaacessivel.model.Saida;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaixaTest {

    private Caixa caixa;

    @Before
    public void configurar() {
        caixa = Caixa.getInstancia();
        caixa.limparMovimentacoes();
    }

    @Test
    public void deveIniciarComSaldoZero() {
        assertEquals(0.00, caixa.calcularSaldo(), 0.001);
    }

    @Test
    public void deveCalcularSaldoComEntradasESaidas() {
        caixa.registrarMovimentacao(
                new Entrada(100.00, "Doação")
        );

        caixa.registrarMovimentacao(
                new Saida(30.00, "Compra de livros")
        );

        assertEquals(70.00, caixa.calcularSaldo(), 0.001);
    }

    @Test
    public void deveListarMovimentacoesRegistradas() {
        caixa.registrarMovimentacao(
                new Entrada(100.00, "Doação")
        );

        caixa.registrarMovimentacao(
                new Saida(25.00, "Compra")
        );

        assertEquals(2, caixa.listarMovimentacoes().size());
    }
}

