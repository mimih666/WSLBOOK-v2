package com.example.wslbook

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wslbook.componentes.BarraDeNavegacao
import com.example.wslbook.telas.TelaAnotacoes
import com.example.wslbook.telas.TelaBiblioteca
import com.example.wslbook.telas.TelaDetalheAnotacao
import com.example.wslbook.telas.TelaDetalheLivro
import com.example.wslbook.telas.TelaInicio
import com.example.wslbook.telas.TelaLeitura
import com.example.wslbook.telas.TelaNovoLivro
import com.example.wslbook.telas.TelaPerfil

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val livros = remember { mutableStateListOf<Livro>().apply { addAll(livrosIniciais()) } }
    val anotacoes = remember { mutableStateListOf<Anotacao>().apply { addAll(anotacoesIniciais()) } }

    val entradaAtual by navController.currentBackStackEntryAsState()
    val rotaAtual = entradaAtual?.destination?.route

    val rotasComBarra = listOf(Rotas.INICIO, Rotas.BIBLIOTECA, Rotas.ANOTACOES, Rotas.PERFIL)

    Scaffold(
        containerColor = Cores.Fundo,
        bottomBar = {
            if (rotaAtual in rotasComBarra) {
                BarraDeNavegacao(navController = navController, rotaAtual = rotaAtual)
            }
        }
    ) { paddingInterno ->

        NavHost(
            navController = navController,
            startDestination = Rotas.INICIO,
            modifier = Modifier.padding(paddingInterno)
        ) {

            composable(Rotas.INICIO) {
                TelaInicio(
                    livros = livros,
                    onAbrirLivro = { id -> navController.navigate(Rotas.detalheLivro(id)) }
                )
            }

            composable(Rotas.BIBLIOTECA) {
                TelaBiblioteca(
                    livros = livros,
                    anotacoes = anotacoes,
                    onAbrirLivro = { id -> navController.navigate(Rotas.detalheLivro(id)) },
                    onNovoLivro = { navController.navigate(Rotas.NOVO_LIVRO) }
                )
            }

            composable(Rotas.NOVO_LIVRO) {
                TelaNovoLivro(
                    livros = livros,
                    onVoltar = { navController.popBackStack() }
                )
            }

            composable(
                route = Rotas.DETALHE_LIVRO,
                arguments = listOf(navArgument(Rotas.ARG_LIVRO_ID) { type = NavType.IntType })
            ) { entrada ->

                val livroId = entrada.arguments?.getInt(Rotas.ARG_LIVRO_ID) ?: -1
                TelaDetalheLivro(
                    livroId = livroId,
                    livros = livros,
                    anotacoes = anotacoes,
                    onVoltar = { navController.popBackStack() },
                    onLer = { id -> navController.navigate(Rotas.leitura(id)) },
                    onAbrirAnotacao = { id -> navController.navigate(Rotas.detalheAnotacao(id)) }
                )
            }

            composable(
                route = Rotas.LEITURA,
                arguments = listOf(navArgument(Rotas.ARG_LIVRO_ID) { type = NavType.IntType })
            ) { entrada ->
                val livroId = entrada.arguments?.getInt(Rotas.ARG_LIVRO_ID) ?: -1
                TelaLeitura(
                    livroId = livroId,
                    livros = livros,
                    onVoltar = { navController.popBackStack() }
                )
            }

            composable(Rotas.ANOTACOES) {
                TelaAnotacoes(
                    livros = livros,
                    anotacoes = anotacoes,
                    onAbrirAnotacao = { id -> navController.navigate(Rotas.detalheAnotacao(id)) }
                )
            }

            composable(
                route = Rotas.DETALHE_ANOTACAO,
                arguments = listOf(navArgument(Rotas.ARG_ANOTACAO_ID) { type = NavType.IntType })
            ) { entrada ->
                val anotacaoId = entrada.arguments?.getInt(Rotas.ARG_ANOTACAO_ID) ?: -1
                TelaDetalheAnotacao(
                    anotacaoId = anotacaoId,
                    livros = livros,
                    anotacoes = anotacoes,
                    onVoltar = { navController.popBackStack() },
                    onAbrirLivro = { id -> navController.navigate(Rotas.detalheLivro(id)) }
                )
            }

            composable(Rotas.PERFIL) {
                TelaPerfil(
                    livros = livros,
                    anotacoes = anotacoes,
                    onAbrirLivro = { id -> navController.navigate(Rotas.detalheLivro(id)) }
                )
            }
        }
    }
}
