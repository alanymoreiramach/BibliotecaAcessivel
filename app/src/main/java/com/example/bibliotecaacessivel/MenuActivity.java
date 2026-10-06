package com.example.bibliotecaacessivel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnLivros;
    private Button btnDoacoes;
    private Button btnFinanceiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        btnLivros = findViewById(R.id.btnLivros);
        btnDoacoes = findViewById(R.id.btnDoacoes);
        btnFinanceiro = findViewById(R.id.btnFinanceiro);

        btnLivros.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuActivity.this,
                    LivrosActivity.class
            );

            startActivity(intent);

        });

        btnDoacoes.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuActivity.this,
                    DoacoesActivity.class
            );

            startActivity(intent);

        });

        btnFinanceiro.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MenuActivity.this,
                    FinanceiroActivity.class
            );

            startActivity(intent);

        });
    }
}