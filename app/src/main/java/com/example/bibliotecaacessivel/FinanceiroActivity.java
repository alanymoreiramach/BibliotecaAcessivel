package com.example.bibliotecaacessivel;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecaacessivel.model.Entrada;
import com.example.bibliotecaacessivel.model.Movimentacao;
import com.example.bibliotecaacessivel.model.Saida;
import com.example.bibliotecaacessivel.service.Caixa;

import java.text.NumberFormat;
import java.util.Locale;

public class FinanceiroActivity extends AppCompatActivity {

    private TextView txtSaldo;
    private LinearLayout containerMovimentacoes;

    private Button btnEntrada;
    private Button btnSaida;

    private Caixa caixa;

    private final NumberFormat formatoMoeda =
            NumberFormat.getCurrencyInstance(
                    new Locale("pt", "BR")
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_financeiro);

        txtSaldo = findViewById(R.id.txtSaldo);
        containerMovimentacoes =
                findViewById(R.id.containerMovimentacoes);

        btnEntrada = findViewById(R.id.btnEntrada);
        btnSaida = findViewById(R.id.btnSaida);

        caixa = Caixa.getInstancia();

        btnEntrada.setOnClickListener(
                v -> abrirDialogoMovimentacao(true)
        );

        btnSaida.setOnClickListener(
                v -> abrirDialogoMovimentacao(false)
        );

        atualizarTela();
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarTela();
    }

    private void abrirDialogoMovimentacao(
            boolean entrada) {

        LinearLayout layout =
                new LinearLayout(this);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        int padding = 40;

        layout.setPadding(
                padding,
                10,
                padding,
                10
        );

        EditText campoValor =
                new EditText(this);

        campoValor.setHint("Valor");

        campoValor.setInputType(
                InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL
        );

        EditText campoDescricao =
                new EditText(this);

        campoDescricao.setHint("Descrição");

        layout.addView(campoValor);
        layout.addView(campoDescricao);

        String titulo = entrada
                ? "Registrar entrada"
                : "Registrar saída";

        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setView(layout)
                .setNegativeButton(
                        "CANCELAR",
                        null
                )
                .setPositiveButton(
                        "SALVAR",
                        (dialog, which) -> {

                            registrarMovimentacao(
                                    entrada,
                                    campoValor,
                                    campoDescricao
                            );
                        }
                )
                .show();
    }

    private void registrarMovimentacao(
            boolean entrada,
            EditText campoValor,
            EditText campoDescricao) {

        String valorTexto =
                campoValor.getText()
                        .toString()
                        .trim();

        String descricao =
                campoDescricao.getText()
                        .toString()
                        .trim();

        if (valorTexto.isEmpty()) {

            Toast.makeText(
                    this,
                    "Informe o valor.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        double valor;

        try {

            valor = Double.parseDouble(
                    valorTexto.replace(",", ".")
            );

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Valor inválido.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (valor <= 0) {

            Toast.makeText(
                    this,
                    "O valor deve ser maior que zero.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (descricao.isEmpty()) {

            Toast.makeText(
                    this,
                    "Informe uma descrição.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Movimentacao movimentacao;

        if (entrada) {

            movimentacao =
                    new Entrada(
                            valor,
                            descricao
                    );

        } else {

            movimentacao =
                    new Saida(
                            valor,
                            descricao
                    );
        }

        caixa.registrarMovimentacao(
                movimentacao
        );

        Toast.makeText(
                this,
                "Movimentação registrada!",
                Toast.LENGTH_SHORT
        ).show();

        atualizarTela();
    }

    private void atualizarTela() {

        double saldo =
                caixa.calcularSaldo();

        txtSaldo.setText(
                formatoMoeda.format(saldo)
        );

        containerMovimentacoes.removeAllViews();

        for (
                Movimentacao movimentacao :
                caixa.listarMovimentacoes()
        ) {

            adicionarMovimentacao(
                    movimentacao
            );
        }
    }

    private void adicionarMovimentacao(
            Movimentacao movimentacao) {

        TextView item =
                new TextView(this);

        double impacto =
                movimentacao.calcularImpacto();

        String sinal =
                impacto >= 0
                        ? "+"
                        : "-";

        item.setText(
                sinal
                        + " "
                        + formatoMoeda.format(
                        Math.abs(impacto)
                )
                        + "\n"
                        + movimentacao.getDescricao()
        );

        item.setTextColor(
                getColor(R.color.texto)
        );

        item.setTextSize(15);

        item.setPadding(
                15,
                15,
                15,
                15
        );

        containerMovimentacoes.addView(
                item
        );
    }
}