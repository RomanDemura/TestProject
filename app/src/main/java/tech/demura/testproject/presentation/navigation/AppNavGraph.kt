package tech.demura.testproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    NewsListContent: @Composable () -> Unit,
    NewsContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsList.route
    ){
        composable(Screen.NewsList.route){
            NewsListContent()
        }
        composable(Screen.News.route){
            NewsContent()
        }
    }
}