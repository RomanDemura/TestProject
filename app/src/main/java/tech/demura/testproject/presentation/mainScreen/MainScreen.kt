package tech.demura.testproject.presentation.mainScreen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import tech.demura.testproject.presentation.NewsListScreen
import tech.demura.testproject.presentation.navigation.AppNavGraph
import tech.demura.testproject.presentation.newsScreen.NewsScreen

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold() {
        AppNavGraph(
            navHostController = navHostController,
            NewsListContent = { NewsListScreen(it) },
            NewsContent = { NewsScreen() }
        )
    }
}