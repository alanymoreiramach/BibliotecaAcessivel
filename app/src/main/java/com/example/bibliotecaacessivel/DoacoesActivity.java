package com.example.bibliotecaacessivel;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecaacessivel.model.Livro;
import com.example.bibliotecaacessivel.service.Biblioteca;

import java.util.ArrayList;
import java.util.List;

public class DoacoesActivity extends AppCompatActivity {

    private Spinner spinnerLivros;
    private Button btnRealizarDoacao;
    private TextView txtNenhumLivro;
    private TextView txtResultadoDoacao;

    private Biblioteca biblioteca;

    private List<Livro> livrosDisponiveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doacoes);

        spinnerLivros = findViewById(R.id.spinnerLivros);
        btnRealizarDoacao = findViewById(R.id.btnRealizarDoacao);
        txtNenhumLivro = findViewById(R.id.txtNenhumLivro);
        txtResultadoDoacao = findViewById(R.id.txtResultadoDoacao);

        biblioteca = Biblioteca.getInstancia();

        btnRealizarDoacao.setOnClickListener(v -> confirmarDoacao());

        carregarLivrosDisponiveis();
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarLivrosDisponiveis();
    }

    private void carregarLivrosDisponiveis() {

        livrosDisponiveis = new ArrayList<>();

        for (Livro livro : biblioteca.listarLivros()) {

            if (livro.isDisponivel()) {
                livrosDisponiveis.add(livro);
            }
        }

        if (livrosDisponiveis.isEmpty()) {

            spinnerLivros.setVisibility(View.GONE);
            btnRealizarDoacao.setVisibility(View.GONE);
            txtNenhumLivro.setVisibility(View.VISIBLE);

            return;
        }

        spinnerLivros.setVisibility(View.VISIBLE);
        btnRealizarDoacao.setVisibility(View.VISIBLE);
        txtNenhumLivro.setVisibility(View.GONE);

        List<String> nomesLivros = new ArrayList<>();

        for (Livro livro : livrosDisponiveis) {
            nomesLivros.add(livro.getTitulo());
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        nomesLivros
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerLivros.setAdapter(adapter);
    }

    private void confirmarDoacao() {

        if (livrosDisponiveis == null ||
                livrosDisponiveis.isEmpty()) {

            return;
        }

        int posicao = spinnerLivros.getSelectedItemPosition();

        Livro livroSelecionado =
                livrosDisponiveis.get(posicao);

        new AlertDialog.Builder(this)
                .setTitle("Confirmar doação")
                .setMessage(
                        "Deseja registrar a doação do livro \""
                                + livroSelecionado.getTitulo()
                                + "\"?"
                )
                .setNegativeButton(
                        "CANCELAR",
                        null
                )
                .setPositiveButton(
                        "CONFIRMAR",
                        (dialog, which) -> realizarDoacao(
                                livroSelecionado
                        )
                )
                .show();
    }

    private void realizarDoacao(Livro livro) {

        boolean sucesso =
                biblioteca.realizarDoacao(
                        livro.getId()
                );

        if (sucesso) {

            txtResultadoDoacao.setText(
                    "Doação registrada!\n\n"
                            + "Livro: "
                            + livro.getTitulo()
                            + "\n"
                            + "O livro foi retirado da disponibilidade."
            );

            Toast.makeText(
                    this,
                    "Doação realizada com sucesso!",
                    Toast.LENGTH_SHORT
            ).show();

            carregarLivrosDisponiveis();

        } else {

            Toast.makeText(
                    this,
                    "Não foi possível realizar a doação.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}