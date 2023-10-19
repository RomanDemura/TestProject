package tech.demura.testproject.presentation.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.demura.testproject.domain.News

class MainScreenViewModel : ViewModel() {

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

    val initialNewsListState = MainScreenState.NewsListScreenState(
        featuredNews = sourceFeaturedList,
        latestNews = sourceLatestList
    )

    private val _screenState = MutableLiveData<MainScreenState>(initialNewsListState)
    val screenState: LiveData<MainScreenState> = _screenState

    private val preState = _screenState

    fun onClick(news: News){
        preState.value = _screenState.value
        _screenState.value = MainScreenState.NewsScreenState(news = news)
    }
}