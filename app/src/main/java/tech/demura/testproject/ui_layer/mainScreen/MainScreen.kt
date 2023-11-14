package tech.demura.testproject.ui_layer.mainScreen

import androidx.compose.runtime.Composable
import tech.demura.testproject.ui_layer.ViewModelFactory
import tech.demura.testproject.ui_layer.navigation.AppNavGraph
import tech.demura.testproject.ui_layer.navigation.rememberNavigationState
import tech.demura.testproject.ui_layer.newsListScreen.NewsListScreen
import tech.demura.testproject.ui_layer.newsScreen.NewsScreen

@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory
) {

    val navigationState = rememberNavigationState()

    AppNavGraph(
        navHostController = navigationState.navHostController,
        newsListScreenContent = {
            NewsListScreen(
                viewModelFactory = viewModelFactory,
                onNewsClick = { navigationState.navigateToNews(it) }
            )
        },
        newsScreenContent = { newsId ->
            NewsScreen(
                newsId = newsId,
                onBackPressed = { navigationState.navHostController.popBackStack() }
            )
        }
    )
}

