package tech.demura.testproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import tech.demura.testproject.domain.News

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