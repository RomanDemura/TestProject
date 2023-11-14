package tech.demura.testproject.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavGraphBuilder.MainScreenNavGraph(
    newsListScreenContent: @Composable () -> Unit,
    newsScreenContent: @Composable (newsId: Int) -> Unit
) {
    navigation(
        startDestination = Screen.NewsList.route,
        route = Screen.MainScreen.route

    ) {
        composable(Screen.NewsList.route) {
            newsListScreenContent()
        }
        composable(route = Screen.News.route,
            arguments = listOf(
                navArgument(name = Screen.KEY_NEWS) {
                    type = NavType.IntType
                }
            )
        ) {
            val newsId = it.arguments?.getInt(Screen.KEY_NEWS) ?: 0
            newsScreenContent(newsId)
        }
    }
}