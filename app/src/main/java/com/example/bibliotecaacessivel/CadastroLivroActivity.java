package com.example.bibliotecaacessivel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecaacessivel.model.Livro;
import com.example.bibliotecaacessivel.service.Biblioteca;

public class CadastroLivroActivity extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtAutor;
    private EditText edtAno;
    private EditText edtCategoria;

    private RadioGroup radioOrigem;
    private RadioButton radioDoacao;
    private RadioButton radioCompra;

    private Button btnSalvar;

    private Biblioteca biblioteca;

    private int livroId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_livro);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtAutor = findViewById(R.id.edtAutor);
        edtAno = findViewById(R.id.edtAno);
        edtCategoria = findViewById(R.id.edtCategoria);

        radioOrigem = findViewById(R.id.radioOrigem);
        radioDoacao = findViewById(R.id.radioDoacao);
        radioCompra = findViewById(R.id.radioCompra);

        btnSalvar = findViewById(R.id.btnSalvar);

        biblioteca = Biblioteca.getInstancia();

        if (getIntent().hasExtra("livro_id")) {

            livroId = getIntent().getIntExtra(
                    "livro_id",
                    -1
            );

            carregarLivroParaEdicao();
        }

        btnSalvar.setOnClickListener(v -> salvarLivro());
    }

    private void carregarLivroParaEdicao() {

        Livro livro = biblioteca.buscarPorId(livroId);

        if (livro == null) {
            return;
        }

        TextView tituloTela = findViewById(R.id.txtTituloTela);

        tituloTela.setText("Editar livro");

        edtTitulo.setText(livro.getTitulo());
        edtAutor.setText(livro.getAutor());
        edtAno.setText(livro.getAno());
        edtCategoria.setText(livro.getCategoria());

        if (livro.getOrigem().equals("Doação")) {
            radioDoacao.setChecked(true);
        } else {
            radioCompra.setChecked(true);
        }

        btnSalvar.setText("SALVAR ALTERAÇÕES");
    }

    private void salvarLivro() {

        String titulo = edtTitulo.getText()
                .toString()
                .trim();

        String autor = edtAutor.getText()
                .toString()
                .trim();

        String ano = edtAno.getText()
                .toString()
                .trim();

        String categoria = edtCategoria.getText()
                .toString()
                .trim();

        if (titulo.isEmpty()) {

            edtTitulo.setError(
                    "Informe o título."
            );

            edtTitulo.requestFocus();

            return;
        }

        if (autor.isEmpty()) {

            edtAutor.setError(
                    "Informe o autor."
            );

            edtAutor.requestFocus();

            return;
        }

        if (categoria.isEmpty()) {

            edtCategoria.setError(
                    "Informe a categoria."
            );

            edtCategoria.requestFocus();

            return;
        }

        String origem;

        if (radioDoacao.isChecked()) {

            origem = "Doação";

        } else if (radioCompra.isChecked()) {

            origem = "Compra";

        } else {

            Toast.makeText(
                    this,
                    "Selecione a origem do livro.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (livroId == -1) {

            biblioteca.adicionarLivro(
                    titulo,
                    autor,
                    ano,
                    categoria,
                    origem
            );

            Toast.makeText(
                    this,
                    "Livro cadastrado com sucesso!",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            Livro livro =
                    biblioteca.buscarPorId(livroId);

            if (livro != null) {

                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setAno(ano);
                livro.setCategoria(categoria);
                livro.setOrigem(origem);

                Toast.makeText(
                        this,
                        "Livro atualizado com sucesso!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }

        finish();
    }
}