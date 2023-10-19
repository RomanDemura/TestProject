package tech.demura.testproject.presentation.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import tech.demura.testproject.presentation.NewsListScreen
import tech.demura.testproject.presentation.newsScreen.NewsScreen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    val screenState = viewModel.screenState.observeAsState(MainScreenState.Initial)

    when (val currentState = screenState.value) {
        is MainScreenState.NewsListScreenState -> {
            NewsListScreen(
                viewModel = viewModel,
                featuredNews = currentState.featuredNews,
                latestNews = currentState.latestNews
            )
        }
        is MainScreenState.NewsScreenState -> {
            NewsScreen(currentState.news)
        }
        MainScreenState.Initial -> {}

    }
}