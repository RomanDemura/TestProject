package tech.demura.testproject.ui_layer.newsListScreen

import tech.demura.testproject.domain_layer.news.entites.News

sealed class NewsListFeaturedState() {
    object Initial : NewsListFeaturedState()
    object Loading: NewsListFeaturedState()
    data class FeaturedNews(
        val featuredNews: List<News>,
        val featuredNewsIsLoading: Boolean = false,
    ) : NewsListFeaturedState()
}
