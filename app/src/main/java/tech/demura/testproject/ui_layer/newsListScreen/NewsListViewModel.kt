package tech.demura.testproject.ui_layer.newsListScreen

import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.demura.testproject.data_layer.repository.NewsRepositoryImpl
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.extensions.mergeWith


class NewsListViewModel : ViewModel() {

    private val repository = NewsRepositoryImpl

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
        viewModelScope.launch {
            loadNextFeaturedNewsEvent.emit(Unit)
            repository.loadNextFeaturedNews()
        }
    }

    fun loadNextLatestdNews() {
        viewModelScope.launch {
            loadNextLatestNewsEvent.emit(Unit)
            repository.loadNextLatestNews()
        }
    }

    // MARK NEWS
    fun markFeaturedNews(news: News) {
        viewModelScope.launch {
            repository.markFeaturedNews(news)
        }
    }

    fun markLatestNews(news: News) {
        viewModelScope.launch {
            repository.markLatestNews(news)
        }
    }

    fun markAllNews() {
        viewModelScope.launch {
            repository.markAllNews()
        }
    }


    // TODO ("Переделать нормально")
    fun getPublishTime(news: News): String {
        val currentTime = System.currentTimeMillis()
        val publishTime = news.publishedDate
        val diff = currentTime - publishTime
        val minutes = diff / DateUtils.MINUTE_IN_MILLIS
        val hours = diff / DateUtils.HOUR_IN_MILLIS
        val days = diff / DateUtils.DAY_IN_MILLIS
        val month = diff / (DateUtils.DAY_IN_MILLIS * 30)
        val years = diff / (DateUtils.DAY_IN_MILLIS * 365)

        val res = if (years >= 1) {
            val check = if (years == 1L) {
                "year"
            } else {
                "years"
            }
            "$years $check ago"
        } else if (month >= 1) {
            val check = if (month == 1L) {
                "month"
            } else {
                "months"
            }
            "$month $check ago"
        } else if (days >= 1) {
            val check = if (days == 1L) {
                "day"
            } else {
                "days"
            }
            "$days $check ago"
        } else if (hours >= 1) {
            val check = if (hours == 1L) {
                "hour"
            } else {
                "hours"
            }
            "$hours $check ago"
        } else if (minutes >= 1) {
            val check = if (minutes == 1L) {
                "minute"
            } else {
                "minutes"
            }
            "$minutes $check ago"
        } else "Now"

        return res
    }


}