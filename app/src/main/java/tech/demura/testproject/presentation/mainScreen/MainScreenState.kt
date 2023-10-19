package tech.demura.testproject.presentation.mainScreen

import tech.demura.testproject.domain.News

sealed class MainScreenState(){
    object Initial: MainScreenState()
    data class NewsListScreenState(val featuredNews: List<News>, val latestNews: List<News>): MainScreenState()
    data class NewsScreenState(val news: News) : MainScreenState()
}
