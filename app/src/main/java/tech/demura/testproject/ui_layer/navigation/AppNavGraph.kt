package tech.demura.testproject.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsListScreenContent: @Composable () -> Unit,
    newsScreenContent: @Composable (newsId: Int) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ) {
        MainScreenNavGraph(
            newsListScreenContent = newsListScreenContent,
            newsScreenContent = newsScreenContent
        )
    }
}