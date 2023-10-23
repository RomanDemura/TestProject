package tech.demura.testproject.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import tech.demura.testproject.domain_layer.news.entites.News

fun NavGraphBuilder.MainScreenNavGraph(
    newsListScreenContent: @Composable () -> Unit,
    newsScreenContent: @Composable (news: News) -> Unit
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
                    type = NavType.StringType
                }
            )
        ) {
            val newsJson = it.arguments?.getString(Screen.KEY_NEWS) ?: ""
            val news = Gson().fromJson(newsJson, News::class.java)
            newsScreenContent(news)
        }
    }
}