package tech.demura.testproject.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import tech.demura.testproject.domain_layer.news.entites.News

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsListScreenContent: @Composable () -> Unit,
    newsScreenContent: @Composable (news: News) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ){
        MainScreenNavGraph(
            newsListScreenContent = newsListScreenContent,
            newsScreenContent = newsScreenContent
        )
    }
}