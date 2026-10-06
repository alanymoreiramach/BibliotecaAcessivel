package com.example.bibliotecaacessivel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecaacessivel.model.Livro;
import com.example.bibliotecaacessivel.service.Biblioteca;

import java.util.List;

public class LivrosActivity extends AppCompatActivity {

    private LinearLayout containerLivros;
    private TextView txtQuantidade;
    private Button btnCadastrarLivro;

    private Biblioteca biblioteca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_livros);

        containerLivros = findViewById(R.id.containerLivros);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        btnCadastrarLivro = findViewById(R.id.btnCadastrarLivro);

        biblioteca = Biblioteca.getInstancia();

        btnCadastrarLivro.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LivrosActivity.this,
                    CadastroLivroActivity.class
            );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarLivros();
    }

    private void carregarLivros() {

        containerLivros.removeAllViews();

        List<Livro> livros = biblioteca.listarLivros();

        txtQuantidade.setText(
                livros.size() + (livros.size() == 1
                        ? " livro cadastrado"
                        : " livros cadastrados")
        );

        for (Livro livro : livros) {

            adicionarCardLivro(livro);
        }
    }

    private void adicionarCardLivro(Livro livro) {

        LinearLayout card = new LinearLayout(this);

        card.setOrientation(LinearLayout.VERTICAL);

        card.setPadding(20, 20, 20, 20);

        TextView titulo = new TextView(this);

        titulo.setText(livro.getTitulo());

        titulo.setTextColor(
                getColor(R.color.marrom_escuro)
        );

        titulo.setTextSize(20);

        titulo.setTypeface(null, 1);

        TextView informacoes = new TextView(this);

        String disponibilidade = livro.isDisponivel()
                ? "Disponível"
                : "Indisponível";

        informacoes.setText(
                "Autor: " + livro.getAutor()
                        + "\nCategoria: " + livro.getCategoria()
                        + "\nOrigem: " + livro.getOrigem()
                        + "\nStatus: " + disponibilidade
        );

        informacoes.setTextColor(
                getColor(R.color.cinza)
        );

        informacoes.setTextSize(14);

        Button btnEditar = new Button(this);

        btnEditar.setText("EDITAR");

        btnEditar.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LivrosActivity.this,
                    CadastroLivroActivity.class
            );

            intent.putExtra("livro_id", livro.getId());

            startActivity(intent);
        });

        Button btnExcluir = new Button(this);

        btnExcluir.setText("EXCLUIR");

        btnExcluir.setOnClickListener(v -> {

            biblioteca.removerLivro(livro.getId());

            Toast.makeText(
                    this,
                    "Livro excluído.",
                    Toast.LENGTH_SHORT
            ).show();

            carregarLivros();
        });

        card.addView(titulo);
        card.addView(informacoes);
        card.addView(btnEditar);
        card.addView(btnExcluir);

        LinearLayout.LayoutParams parametros =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        parametros.setMargins(0, 0, 0, 20);

        containerLivros.addView(card, parametros);
    }
}