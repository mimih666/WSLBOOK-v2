package com.example.wslbook

import androidx.compose.ui.graphics.Color

data class Livro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val genero: String,
    val totalPaginas: Int,
    val paginasLidas: Int = 0,
    val capa: Int = R.drawable.capa
) {

    fun porcentagemLida(): Int =
        if (totalPaginas <= 0) 0 else (paginasLidas * 100) / totalPaginas

    fun paginasRestantes(): Int = totalPaginas - paginasLidas

    fun status(): String = when {
        paginasLidas <= 0 -> "Quero ler"
        paginasLidas >= totalPaginas -> "Lido"
        else -> "Lendo"
    }
}

data class Anotacao(
    val id: Int,
    val livroId: Int,
    val trecho: String,
    val pagina: Int,
    val favorita: Boolean = false
)

object Rotas {
    const val INICIO = "inicio"
    const val BIBLIOTECA = "biblioteca"
    const val ANOTACOES = "anotacoes"
    const val PERFIL = "perfil"

    const val NOVO_LIVRO = "novo_livro"

    const val ARG_LIVRO_ID = "livroId"
    const val ARG_ANOTACAO_ID = "anotacaoId"
    const val DETALHE_LIVRO = "detalhe_livro/{livroId}"
    const val DETALHE_ANOTACAO = "detalhe_anotacao/{anotacaoId}"
    const val LEITURA = "leitura/{livroId}"

    fun detalheLivro(id: Int) = "detalhe_livro/$id"
    fun detalheAnotacao(id: Int) = "detalhe_anotacao/$id"
    fun leitura(id: Int) = "leitura/$id"
}

object Cores {
    val Fundo = Color(0xFF121212)
    val FundoCard = Color(0xFF1E1E1E)
    val Vermelho = Color(0xFFD9364F)
    val Creme = Color(0xFFFFF8E7)
    val Cinza = Color(0xFF9E9E9E)
}

val generos = listOf("Romance", "Fantasia", "Terror", "Suspense", "Ficção")

fun livrosIniciais(): List<Livro> = listOf(
    //    id  título                                   autor(a)              gênero      págs  lidas  capa
    Livro(1,  "Harry Potter e a Pedra Filosofal",      "J.K. Rowling",       "Fantasia", 264,  264,   R.drawable.img_10),
    Livro(2,  "O Príncipe Cruel",                      "Holly Black",        "Fantasia", 312,  150,   R.drawable.img_3),
    Livro(3,  "Noiva",                                 "Ali Hazelwood",      "Romance",  416,  0,     R.drawable.img_4),
    Livro(4,  "Amor Bobo",                             "Thaiza Diniz",       "Romance",  304,  304,   R.drawable.img_8),
    Livro(5,  "Nos Constellations",                    "Florence Quentin",   "Romance",  288,  0,     R.drawable.img_9),
    Livro(6,  "Saboroso Cadáver",                      "Agustina Bazterrica","Terror",   256,  90,    R.drawable.img_6),
    Livro(7,  "Não Pisque",                            "Stephen King",       "Terror",   320,  0,     R.drawable.img_7),
    Livro(8,  "Lady Killers: Assassinas em Série",     "Tori Telfer",        "Suspense", 336,  336,   R.drawable.img_1),
    Livro(9,  "1984",                                  "George Orwell",      "Ficção",   416,  208,   R.drawable.img_5),
    Livro(10, "A Biblioteca da Meia-Noite",            "Matt Haig",          "Ficção",   308,  0,     R.drawable.img_2),
    Livro(11, "A Princesa Salva a Si Mesma Neste Livro","Amanda Lovelace",   "Ficção",   208,  208,   R.drawable.img)
)

fun anotacoesIniciais(): List<Anotacao> = listOf(
    Anotacao(1, 1, "Primeira vez que entrei em Hogwarts. Reler nas férias!", 80, true),
    Anotacao(2, 2, "A Jude é muito mais esperta do que todo mundo pensa.", 120, true),
    Anotacao(3, 9, "Guerra é paz. Liberdade é escravidão. Ignorância é força.", 26, true),
    Anotacao(4, 6, "Não consegui dormir depois deste capítulo...", 60),
    Anotacao(5, 8, "Inacreditável que essas histórias são reais.", 150)
)
