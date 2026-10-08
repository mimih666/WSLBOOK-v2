# 📚 WSL BOOK: MAF (Mínimo Aplicativo Funcional)

Aplicativo Android de leitura e organização de livros, feito em **Kotlin + Jetpack Compose**.
Trabalho 2 de Desenvolvimento de Aplicativos Móveis, continuação do Trabalho 1 (3 telas estilizadas).

Neste trabalho o app passou a ter **navegação real entre 8 telas** e **duas listas que mudam de verdade** (Livros e Anotações).

---

## ✨ O que o app faz

| Tela | O que dá para fazer |
|---|---|
| **Início** | Ver os livros "Continue lendo", **pesquisar** por título/autor, **filtrar por gênero** e abrir qualquer livro do catálogo |
| **Biblioteca** (lista de Livros) | Ver todos os livros em cards, filtrar por status, **adicionar** (botão "Adicionar") e **remover** (lixeira) |
| **Novo livro** | Formulário com título, autor, gênero, total de páginas e páginas lidas (com validação) |
| **Detalhe do livro** | Progresso **calculado** (%, páginas restantes, dias para terminar), **editar o progresso ali mesmo** (-10 / +10 / Concluir), ver as **anotações daquele livro** e ir para a Leitura |
| **Leitura** | Texto com ajuste de fonte (A- / A+), favoritar, botões de página que **atualizam o progresso do livro** |
| **Anotações** (lista de Anotações) | **Adicionar** trecho (escolhendo o livro, texto de várias linhas e página), **marcar favorita** (Checkbox), **remover** (lixeira) e filtrar favoritas |
| **Detalhe da anotação** | Trecho completo, **posição calculada no livro**, favoritar, excluir e abrir o livro dono da anotação |
| **Perfil** | Botão Seguir funcional, estatísticas e meta de leitura **calculadas a partir das listas**, livros lidos clicáveis |

A barra de navegação inferior liga as 4 áreas principais: **Início · Biblioteca · Anotações · Perfil**.

> Os dados ficam só na memória (`mutableStateListOf`). Ao fechar o app, as alterações se perdem. Isso é esperado neste trabalho (seção 5 do enunciado).

---

## ▶️ Como rodar o projeto

### Pré-requisitos
- **Android Studio** (versão recente)
- **JDK 17 ou superior** (já vem com o Android Studio)
- Um **emulador** Android (ex: Pixel) ou um **celular** com depuração USB ligada

### Passo a passo
1. Clone o repositório:
   ```bash
   git clone <URL-DESTE-REPOSITORIO>
   ```
2. No Android Studio: **File → Open** e escolha a pasta do projeto.
3. Espere o **Gradle Sync** terminar (barra de progresso no canto inferior).
4. Escolha o emulador ou o celular na barra de cima.
5. Clique em **Run ▶** (ou `Shift + F10`).

### Se der erro no Sync
- **Erro de "AAR metadata" / compileSdk**: em `gradle/libs.versions.toml`, use `coreKtx = "1.13.1"` e `lifecycleRuntimeKtx = "2.8.7"`.
- **Erro de cache do Gradle ("immutable workspace")**: **File → Invalidate Caches → Invalidate and Restart**.

---

## 🗂️ Organização do código

```
app/src/main/java/com/example/wslbook/
├── MainActivity.kt          ← única Activity, só chama o AppNavigation
├── AppNavigation.kt         ← NavHost + as duas listas do app
├── Modelos.kt               ← data classes (Livro, Anotacao), objeto Rotas, cores, dados iniciais
├── componentes/
│   └── Componentes.kt       ← peças reutilizáveis (BottomBar, TopAppBar, capa, filtro, barra de progresso)
└── telas/
    ├── TelaInicio.kt
    ├── TelaBiblioteca.kt     (lista de Livros)
    ├── TelaNovoLivro.kt      (formulário)
    ├── TelaDetalheLivro.kt
    ├── TelaLeitura.kt
    ├── TelaAnotacoes.kt      (lista de Anotações)
    ├── TelaDetalheAnotacao.kt
    └── TelaPerfil.kt
```

📄 A história do processo e as decisões do trio estão em **[PROCESSO.md](PROCESSO.md)**.

---

## 🧰 Tecnologias e componentes usados
Kotlin · Jetpack Compose · Material 3 · Navigation Compose (`NavHost`, `NavController`, `navigate`, `popBackStack`, rotas com argumento) · `LazyColumn`, `LazyRow`, `LazyVerticalGrid` · `Card` · `mutableStateListOf` · `remember` / `mutableStateOf` · `OutlinedTextField` (texto, numérico, várias linhas) · `Checkbox` · `TopAppBar` · `NavigationBar` · `Scaffold`

## 👥 Integrantes
- _Nome 1_
- _Nome 2_
- _Nome 3_
