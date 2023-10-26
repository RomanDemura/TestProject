package tech.demura.testproject.ui_layer.newsListScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import tech.demura.testproject.data_layer.repository.NewsRepositoryImpl
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.extensions.getTimeAgo
import tech.demura.testproject.extensions.mergeWith


class NewsListViewModel : ViewModel() {

    private val repository = NewsRepositoryImpl
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("NewsListViewModel", "Caught Exception")
    }

    //FEATURED NEWS FLOW
    private val loadNextFeaturedNewsEvent = MutableSharedFlow<Unit>()
    private val featuredNewsFlow = repository.featuredNewsFlow
    private val loadNextFeaturedNewsFlow = flow {
        loadNextFeaturedNewsEvent.collect {
            emit(
                NewsListFeaturedState.FeaturedNews(
                    featuredNews = featuredNewsFlow.value,
                    featuredNewsIsLoading = true
                )
            )
        }
    }
    val featuredNewsState = featuredNewsFlow
        .filter { it.isNotEmpty() }
        .map { NewsListFeaturedState.FeaturedNews(featuredNews = it) }
        .onStart { NewsListFeaturedState.Loading }
        .mergeWith(loadNextFeaturedNewsFlow)

    // LATEST NEWS FLOW
    private val loadNextLatestNewsEvent = MutableSharedFlow<Unit>()
    private val latestNewsFlow = repository.latestNewsFlow
    private val loadNextLatestNewsFlow = flow {
        loadNextLatestNewsEvent.collect {
            emit(
                NewsListLatestState.LatestNews(
                    latestNews = latestNewsFlow.value,
                    latestNewsIsLoading = true
                )
            )
        }
    }
    val latestNewsState = latestNewsFlow
        .filter { it.isNotEmpty() }
        .map { NewsListLatestState.LatestNews(latestNews = it) }
        .onStart { NewsListLatestState.Loading }
        .mergeWith(loadNextLatestNewsFlow)


    // LOAD NEXT
    fun loadNextFeaturedNews() {
        viewModelScope.launch(exceptionHandler) {
            loadNextFeaturedNewsEvent.emit(Unit)
            repository.loadNextFeaturedNews()
        }
    }

    fun loadNextLatestdNews() {
        viewModelScope.launch(exceptionHandler) {
            loadNextLatestNewsEvent.emit(Unit)
            repository.loadNextLatestNews()
        }
    }

    // MARK NEWS
    fun markFeaturedNews(news: News) {
        viewModelScope.launch(exceptionHandler) {
            repository.markFeaturedNews(news)
        }
    }

    fun markLatestNews(news: News) {
        viewModelScope.launch(exceptionHandler) {
            repository.markLatestNews(news)
        }
    }

    fun markAllNews() {
        viewModelScope.launch(exceptionHandler) {
            repository.markAllNews()
        }
    }

    fun convertNewsPublishDateToTimeAgo(news: News): String {
        return news.publishedDate.getTimeAgo()
    }

}