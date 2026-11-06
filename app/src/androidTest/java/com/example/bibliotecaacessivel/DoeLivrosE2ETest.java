
package com.example.bibliotecaacessivel;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DoeLivrosE2ETest {

    private static final String TITULO = "Livro de Teste E2E - DoeLivros";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void deveCadastrarLivroERealizarDoacao() {

        // 1. Entrar no aplicativo
        onView(withId(R.id.btnEntrar))
                .perform(click());

        // 2. Acessar a tela Meus Livros
        onView(withId(R.id.btnLivros))
                .perform(click());

        // 3. Abrir o formulário de cadastro
        onView(withId(R.id.btnCadastrarLivro))
                .perform(click());

        // 4. Preencher os dados do livro
        onView(withId(R.id.edtTitulo))
                .perform(replaceText(TITULO));

        onView(withId(R.id.edtAutor))
                .perform(replaceText("Antoine de Saint-Exupery"));

        onView(withId(R.id.edtAno))
                .perform(replaceText("1943"));

        onView(withId(R.id.edtCategoria))
                .perform(replaceText("Literatura"), closeSoftKeyboard());

        // Selecionar a origem do livro
        onView(withId(R.id.radioDoacao))
                .perform(click());

        // 5. Salvar o cadastro
        onView(withId(R.id.btnSalvar))
                .perform(scrollTo(), click());

        // 6. Verificar se o livro aparece na listagem
        onView(withText(TITULO))
                .check(matches(withText(TITULO)));

        // 7. Voltar ao menu principal
        pressBack();

        // 8. Acessar a tela de doações
        onView(withId(R.id.btnDoacoes))
                .perform(click());

        // 9. Abrir a lista de livros disponíveis
        onView(withId(R.id.spinnerLivros))
                .perform(click());

        // 10. Selecionar o livro cadastrado
        onData(is(TITULO))
                .perform(click());

        // 11. Solicitar a realização da doação
        onView(withId(R.id.btnRealizarDoacao))
                .perform(click());

        // 12. Confirmar a doação no diálogo
        onView(withText("CONFIRMAR"))
                .perform(click());

        // 13. Verificar a mensagem de sucesso
        onView(withId(R.id.txtResultadoDoacao))
                .check(matches(withText(
                        org.hamcrest.Matchers.containsString(
                                "Doação registrada!"
                        )
                )));

        // 14. Voltar ao menu e abrir novamente a listagem
        pressBack();

        onView(withId(R.id.btnLivros))
                .perform(click());

        // 15. Verificar se o livro continua cadastrado,
        // mas agora está indisponível
        onView(withId(R.id.containerLivros))
                .check((view, noViewFoundException) -> {

                    LinearLayout container = (LinearLayout) view;
                    boolean livroIndisponivelEncontrado = false;

                    for (int i = 0; i < container.getChildCount(); i++) {

                        View filho = container.getChildAt(i);

                        if (filho instanceof LinearLayout) {

                            LinearLayout card = (LinearLayout) filho;

                            TextView titulo = (TextView) card.getChildAt(0);
                            TextView informacoes = (TextView) card.getChildAt(1);

                            if (titulo.getText().toString().equals(TITULO)
                                    && informacoes.getText().toString()
                                    .contains("Status: Indisponível")) {

                                livroIndisponivelEncontrado = true;
                                break;
                            }
                        }
                    }

                    assertTrue(
                            "O livro deveria continuar cadastrado e estar indisponível.",
                            livroIndisponivelEncontrado
                    );
                });
    }
}
