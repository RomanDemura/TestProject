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
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.entites.NewsListFeaturedState
import tech.demura.testproject.domain_layer.news.entites.NewsListLatestState
import tech.demura.testproject.domain_layer.news.use_case.GetFeaturedNewsFlowUseCase
import tech.demura.testproject.domain_layer.news.use_case.GetLatestNewsFlowUseCase
import tech.demura.testproject.domain_layer.news.use_case.LoadNextFeaturedNewsUseCase
import tech.demura.testproject.domain_layer.news.use_case.LoadNextLatestNewsUseCase
import tech.demura.testproject.domain_layer.news.use_case.MarkAllNewsUseCase
import tech.demura.testproject.domain_layer.news.use_case.MarkFeaturedNewsUseCase
import tech.demura.testproject.domain_layer.news.use_case.MarkLatestNewsUseCase
import tech.demura.testproject.extensions.getTimeAgo
import tech.demura.testproject.extensions.mergeWith
import javax.inject.Inject


class NewsListViewModel @Inject constructor(
    private val getFeaturedNewsFlowUseCase: GetFeaturedNewsFlowUseCase,
    private val getLatestNewsFlowUseCase: GetLatestNewsFlowUseCase,
    private val loadNextFeaturedNewsUseCase: LoadNextFeaturedNewsUseCase,
    private val loadNextLatestNewsUseCase: LoadNextLatestNewsUseCase,
    private val markFeaturedNewsUseCase: MarkFeaturedNewsUseCase,
    private val markLatestNewsUseCase: MarkLatestNewsUseCase,
    private val markAllNewsUseCase: MarkAllNewsUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("NewsListViewModel", "Caught Exception")
    }

    //FEATURED NEWS FLOW
    private val loadNextFeaturedNewsEvent = MutableSharedFlow<Unit>()
    private val featuredNewsFlow = getFeaturedNewsFlowUseCase()
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
    private val latestNewsFlow = getLatestNewsFlowUseCase()
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
            loadNextFeaturedNewsUseCase()
        }
    }

    fun loadNextLatestdNews() {
        viewModelScope.launch(exceptionHandler) {
            loadNextLatestNewsEvent.emit(Unit)
            loadNextLatestNewsUseCase()
        }
    }

    // MARK NEWS
    fun markFeaturedNews(news: News) {
        viewModelScope.launch(exceptionHandler) {
            markFeaturedNewsUseCase(news)
        }
    }

    fun markLatestNews(news: News) {
        viewModelScope.launch(exceptionHandler) {
            markLatestNewsUseCase(news)
        }
    }

    fun markAllNews() {
        viewModelScope.launch(exceptionHandler) {
            markAllNewsUseCase()
        }
    }

    fun convertNewsPublishDateToTimeAgo(news: News): String {
        return news.publishedDate.getTimeAgo()
    }

}