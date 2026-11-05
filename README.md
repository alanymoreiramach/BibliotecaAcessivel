# 📚 DoeLivros — Sistema de Gerenciamento de Biblioteca

> Aplicativo Android desenvolvido em Java para auxiliar no gerenciamento de uma pequena biblioteca comunitária, facilitando o controle de livros, doações e movimentações financeiras.

---

## 1. Sobre o projeto

O **DoeLivros** é um aplicativo desenvolvido como projeto acadêmico do curso de Engenharia de Software. Sua proposta é oferecer uma ferramenta simples, acessível e intuitiva para o gerenciamento de uma biblioteca de pequeno porte.

O sistema foi pensado para atender à realidade de um ponto de arrecadação, organização e doação de livros, no qual uma única pessoa pode administrar o acervo e acompanhar as movimentações realizadas.

Diferentemente de sistemas tradicionais de bibliotecas acadêmicas, o DoeLivros não tem como objetivo gerenciar alunos, professores, matrículas ou empréstimos institucionais. Seu foco está na administração do acervo físico, no registro da origem dos livros e no controle básico dos recursos financeiros.

O desenvolvimento prioriza uma interface acolhedora, facilidade de utilização, organização do código e aplicação dos conceitos de Programação Orientada a Objetos.

## 2. Objetivos

### Objetivo geral

Desenvolver um aplicativo Android que facilite o gerenciamento de uma pequena biblioteca, permitindo organizar livros, registrar doações e controlar entradas e saídas financeiras em um único ambiente.

### Objetivos específicos

- Permitir o cadastro, a consulta, a edição e a exclusão de livros.
- Registrar a origem dos livros, diferenciando compras e doações.
- Organizar automaticamente o acervo em ordem alfabética.
- Controlar a disponibilidade dos livros.
- Registrar a saída de livros por meio de doações.
- Controlar entradas e saídas financeiras.
- Calcular e apresentar o saldo atual.
- Oferecer uma interface simples e acessível.
- Preservar as informações cadastradas após o encerramento do aplicativo.

## 3. Funcionalidades

### 📖 3.1. Gerenciamento de livros

O módulo de livros permite administrar os registros do acervo.

Funcionalidades:

- Cadastrar novos livros.
- Informar título, autor, ano e categoria.
- Identificar a origem do livro: compra ou doação.
- Consultar os livros cadastrados.
- Editar informações de um livro.
- Excluir registros.
- Visualizar a disponibilidade de cada livro.
- Organizar os registros alfabeticamente pelo título.

### 🤝 3.2. Gerenciamento de doações

O módulo de doações permite registrar a saída de livros disponíveis no acervo.

Funcionalidades:

- Consultar os livros disponíveis para doação.
- Selecionar o livro desejado.
- Confirmar a operação antes de realizá-la.
- Alterar o status do livro para indisponível.
- Atualizar a listagem após a operação.

A doação não exclui o registro do livro. O sistema mantém suas informações e altera apenas seu status de disponibilidade.

### 💰 3.3. Controle financeiro

O módulo financeiro permite acompanhar as movimentações básicas de recursos da biblioteca.

Funcionalidades:

- Registrar entradas financeiras.
- Registrar saídas financeiras.
- Informar o valor e a descrição da movimentação.
- Calcular automaticamente o saldo.
- Exibir o histórico de movimentações.
- Apresentar valores no formato monetário brasileiro (R$).

Exemplos de movimentações:

| Tipo | Exemplo |
|---|---|
| Entrada | Doação em dinheiro |
| Entrada | Contribuição voluntária |
| Saída | Compra de livros |
| Saída | Despesas de manutenção |

O cálculo do saldo considera a soma das entradas e a subtração das saídas.

## 4. Tecnologias utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java | Linguagem de programação principal |
| Android Studio | Ambiente de desenvolvimento |
| XML | Construção das interfaces |
| Android SDK | Recursos e componentes Android |
| SharedPreferences | Armazenamento local de informações |
| JSON | Organização dos dados para persistência |

O projeto utiliza desenvolvimento nativo Android, com Activities para controlar as telas e arquivos XML para estruturar as interfaces.

**Observação sobre armazenamento:** a persistência dos livros está sendo implementada utilizando SharedPreferences associado à conversão dos objetos para JSON. O armazenamento das movimentações financeiras também deverá utilizar esse mecanismo para preservar o histórico e o saldo entre execuções. A implementação deve ser validada no aplicativo antes de considerar a persistência integral concluída.

## 5. Organização do projeto

A estrutura lógica adotada separa os modelos de dados, as regras de negócio e o armazenamento.

```text
BibliotecaAcessivel/
│
└── app/
    └── src/
        └── main/
            │
            ├── java/
            │   └── com/
            │       └── example/
            │           └── bibliotecaacessivel/
            │               │
            │               ├── MainActivity.java
            │               ├── MenuActivity.java
            │               ├── LivrosActivity.java
            │               ├── CadastroLivroActivity.java
            │               ├── DoacoesActivity.java
            │               ├── FinanceiroActivity.java
            │               │
            │               ├── model/
            │               │   ├── Livro.java
            │               │   ├── Movimentacao.java
            │               │   ├── Entrada.java
            │               │   └── Saida.java
            │               │
            │               ├── service/
            │               │   ├── Biblioteca.java
            │               │   └── Caixa.java
            │               │
            │               └── storage/
            │                   └── Armazenamento.java
            │
            ├── res/
            │   ├── layout/
            │   ├── drawable/
            │   ├── mipmap/
            │   └── values/
            │       ├── colors.xml
            │       ├── strings.xml
            │       └── themes.xml
            │
            └── AndroidManifest.xml
```

A estrutura apresenta a organização lógica prevista para o projeto. Os arquivos podem variar conforme a evolução da implementação.

## 6. Arquitetura e organização das classes

O código foi organizado para separar responsabilidades e facilitar futuras alterações.

### 6.1. Camada de apresentação

Responsável pela interação com o usuário.

| Classe | Responsabilidade |
|---|---|
| `MainActivity` | Tela inicial do aplicativo |
| `MenuActivity` | Menu principal e navegação |
| `LivrosActivity` | Exibição e gerenciamento do acervo |
| `CadastroLivroActivity` | Cadastro e edição de livros |
| `DoacoesActivity` | Registro de doações |
| `FinanceiroActivity` | Controle financeiro |

### 6.2. Camada de modelos — `model`

Representa as entidades e os conceitos utilizados pelo sistema.

**Livro.java**

Representa um livro cadastrado, contendo:

- Identificador;
- Título;
- Autor;
- Ano;
- Categoria;
- Origem;
- Disponibilidade.

A classe utiliza atributos privados e métodos de acesso, aplicando o conceito de encapsulamento.

**Movimentacao.java**

Classe abstrata que representa uma movimentação financeira. Contém valor e descrição, além do método abstrato `calcularImpacto()`.

**Entrada.java**

Especialização de `Movimentacao`, responsável por representar valores adicionados ao caixa. Sua implementação retorna um impacto financeiro positivo.

**Saida.java**

Especialização de `Movimentacao`, responsável por representar valores retirados do caixa. Sua implementação retorna um impacto financeiro negativo.

### 6.3. Camada de serviços — `service`

Responsável pelas regras de negócio.

**Biblioteca.java**

Centraliza o gerenciamento dos livros, incluindo:

- Cadastro;
- Listagem;
- Ordenação alfabética;
- Busca por identificador;
- Edição;
- Exclusão;
- Registro de doações;
- Atualização da disponibilidade.

**Caixa.java**

Centraliza as operações financeiras, incluindo:

- Registro de movimentações;
- Listagem do histórico;
- Cálculo do saldo.

### 6.4. Camada de armazenamento — `storage`

**Armazenamento.java**

Responsável pela persistência local dos dados utilizando SharedPreferences.

A proposta é converter os objetos para JSON antes de armazená-los e reconstruir os objetos quando o aplicativo for iniciado novamente.

Essa organização evita que cada tela precise implementar diretamente as operações de armazenamento.

## 7. Conceitos de Programação Orientada a Objetos

O projeto utiliza conceitos fundamentais de Programação Orientada a Objetos.

| Conceito | Aplicação no projeto |
|---|---|
| Classes e objetos | `Livro`, `Biblioteca`, `Caixa` e demais classes |
| Encapsulamento | Atributos privados e métodos de acesso |
| Herança | `Entrada` e `Saida` herdam de `Movimentacao` |
| Abstração | `Movimentacao` define uma estrutura comum para operações financeiras |
| Polimorfismo | O método `calcularImpacto()` possui implementações diferentes em `Entrada` e `Saida` |

O uso desses conceitos contribui para a organização, reutilização e manutenção do código.

## 8. Interface e acessibilidade

A interface foi planejada para apresentar uma experiência simples e acolhedora.

A identidade visual utiliza uma combinação de tons neutros e naturais, com destaque para:

- Creme;
- Marrom;
- Bege;
- Verde-sálvia.

Entre as preocupações de interface estão:

- Textos objetivos;
- Botões com identificação clara;
- Organização das informações;
- Confirmação de operações importantes;
- Navegação simplificada;
- Contraste entre elementos e plano de fundo.

O objetivo é reduzir a complexidade das operações e tornar o aplicativo compreensível para pessoas com diferentes níveis de familiaridade com tecnologia.

## 9. Como executar o projeto

### Pré-requisitos

- Android Studio instalado;
- Android SDK configurado;
- JDK compatível com a configuração do projeto;
- Emulador Android ou dispositivo físico com depuração USB habilitada.

### Execução

1. Abra o Android Studio.
2. Selecione a opção **Open**.
3. Escolha a pasta raiz do projeto.
4. Aguarde a sincronização do Gradle.
5. Conecte um dispositivo Android ou inicie um emulador.
6. Execute o projeto utilizando o botão **Run**.
7. Aguarde a instalação e abertura do aplicativo.

## 10. Persistência dos dados

Um dos requisitos importantes do DoeLivros é preservar as informações cadastradas.

A aplicação utiliza armazenamento local, sem depender de conexão com a internet ou de um servidor externo.

O mecanismo proposto é:

```text
Objeto Java
    ↓
Conversão para JSON
    ↓
SharedPreferences
    ↓
Armazenamento local
    ↓
Leitura dos dados
    ↓
Reconstrução dos objetos
```

Dessa forma, os dados poderão continuar disponíveis após o fechamento e a reabertura do aplicativo, desde que a implementação da persistência esteja concluída e validada.

Os dados armazenados localmente são removidos quando o aplicativo é desinstalado ou quando seus dados são apagados pelas configurações do Android. Por isso, cópias de segurança poderão ser consideradas em versões futuras.

## 11. Regras de negócio

O sistema considera as seguintes regras:

1. Todo livro cadastrado recebe um identificador próprio.
2. O título, o autor e a categoria são informações obrigatórias.
3. Todo livro deve possuir uma origem identificada como compra ou doação.
4. Os livros são apresentados em ordem alfabética pelo título.
5. Um livro disponível pode ser selecionado para doação.
6. Após a doação, o livro passa a apresentar status indisponível.
7. O registro do livro permanece no sistema após a doação.
8. Uma movimentação financeira deve possuir valor positivo e descrição.
9. Entradas aumentam o saldo.
10. Saídas diminuem o saldo.

## 12. Limitações e funcionalidades futuras

O DoeLivros está sendo desenvolvido de forma incremental. Algumas funcionalidades poderão ser incorporadas conforme a evolução do projeto.

Possíveis melhorias:

- Persistência completa do histórico financeiro.
- Backup e restauração dos dados.
- Relatórios de livros cadastrados e doações realizadas.
- Histórico detalhado de entrada e saída de livros.
- Filtros por categoria, autor e origem.
- Melhorias de acessibilidade, como ampliação de fonte e compatibilidade com leitores de tela.
- Testes automatizados das regras de negócio.
- Validação do protótipo com usuários reais.
- Exportação de relatórios.

Essas melhorias não devem ser confundidas com funcionalidades já implementadas.

## 13. Testes e validação

A validação do aplicativo deverá contemplar os principais fluxos de utilização:

| Módulo | Cenário de teste |
|---|---|
| Livros | Cadastrar um livro com informações válidas |
| Livros | Impedir cadastro sem informações obrigatórias |
| Livros | Editar um livro existente |
| Livros | Excluir um livro |
| Livros | Verificar ordenação alfabética |
| Doações | Realizar doação de livro disponível |
| Doações | Impedir nova doação de livro indisponível |
| Financeiro | Registrar uma entrada |
| Financeiro | Registrar uma saída |
| Financeiro | Conferir o cálculo do saldo |
| Persistência | Fechar e reabrir o aplicativo e verificar os dados |

Os testes devem verificar tanto o comportamento esperado quanto a preservação das informações.

## 14. Considerações finais

O DoeLivros busca demonstrar como conceitos de Engenharia de Software e Programação Orientada a Objetos podem ser aplicados no desenvolvimento de uma solução prática.

A proposta prioriza a simplicidade, a organização das informações e a facilidade de utilização, considerando as necessidades de uma biblioteca de pequeno porte.

A separação das responsabilidades em modelos, serviços e armazenamento permite que o sistema evolua gradualmente, facilitando a manutenção e a inclusão de novas funcionalidades.

---

## 15. Autoria

Projeto acadêmico desenvolvido para o curso de **Engenharia de Software**.

**Desenvolvimento:** Alany Moreira Mach e Samara Ostheimer Kubes .

**Tecnologia principal:** Java — Android.

**Ambiente de desenvolvimento:** Android Studio.

**Projeto:** DoeLivros — Sistema de Gerenciamento de Biblioteca.
