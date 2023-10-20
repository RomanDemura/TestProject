package tech.demura.testproject.presentation.newsListScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.demura.testproject.domain.News

class NewsListViewModel : ViewModel() {
    val sourceFeaturedList = mutableListOf<News>().apply {
        repeat(20) {
            val news = News(id = it, title = "Featured $it")
            add(news)
        }
    }
    val sourceLatestList = mutableListOf<News>().apply {
        repeat(20) {
            val news = News(id = it, title = "Latest $it")
            add(news)
        }
    }

    val initialNewsListState = NewsListScreenState.NewsList(
        featuredNews = sourceFeaturedList,
        latestNews = sourceLatestList
    )

    private val _screenState = MutableLiveData<NewsListScreenState>(initialNewsListState)
    val screenState: LiveData<NewsListScreenState> = _screenState

}