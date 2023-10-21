package tech.demura.testproject.ui_layer.newsListScreen

import tech.demura.testproject.domain_layer.news.entites.News

sealed class NewsListScreenState() {
    object Initial: NewsListScreenState()
    data class NewsList(val featuredNews: List<News>, val latestNews: List<News>): NewsListScreenState()
}
