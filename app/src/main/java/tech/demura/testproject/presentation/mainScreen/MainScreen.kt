package tech.demura.testproject.presentation.mainScreen

import androidx.compose.runtime.Composable
import tech.demura.testproject.presentation.navigation.AppNavGraph
import tech.demura.testproject.presentation.navigation.rememberNavigationState
import tech.demura.testproject.presentation.newsListScreen.NewsListScreen
import tech.demura.testproject.presentation.newsScreen.NewsScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    AppNavGraph(
        navHostController = navigationState.navHostController,
        newsListScreenContent = {
            NewsListScreen(
                onNewsClick = { navigationState.navigateToNews(it) })
        },
        newsScreenContent = { news ->
            NewsScreen(
                news = news,
                onBackPressed = { navigationState.navHostController.popBackStack() }
            )
        }
    )
}