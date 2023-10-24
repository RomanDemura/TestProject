package tech.demura.testproject.ui_layer.newsListScreen

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.demura.testproject.data_layer.repository.NewsRepositoryImpl
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.use_case.*


class NewsListViewModel : ViewModel() {
//    private val getAllFeaturedNewsUseCase = getAllFeaturedNewsUseCase(repository)
//    private val getAllLatestNewsUseCase = getAllLatestNewsUseCase(repository)
//
//    private val featuredNews = getAllFeaturedNewsUseCase.getAllNews()
//    private val latestNews = getAllLatestNewsUseCase.getAllNews()

    private val _featuredNewsState =
        MutableLiveData<NewsListFeaturedState>(NewsListFeaturedState.Initial)
    val featuredNewsState: LiveData<NewsListFeaturedState> = _featuredNewsState

    private val _latestNewsState =
        MutableLiveData<NewsListLatestState>(NewsListLatestState.Initial)
    val latestNewsState: LiveData<NewsListLatestState> = _latestNewsState

    private val repository = NewsRepositoryImpl

    private val markFeaturedNewsUseCase = markFeaturedNewsUseCase(repository)
    private val markLatestNewsUseCase = markLatestNewsUseCase(repository)
    private val markAllNewsUseCase = markAllNewsUseCase(repository)

    init {
        _latestNewsState.value = NewsListLatestState.Loading
        loadLatestNews()
        _featuredNewsState.value = NewsListFeaturedState.Loading
        loadFeaturedNews()
    }

    private fun loadFeaturedNews() {
        viewModelScope.launch {
            val featuredNews = repository.loadCatFacts()
            _featuredNewsState.value =
                NewsListFeaturedState.FeaturedNews(
                    featuredNews = featuredNews,
                    featuredNewsIsLoading = false
                )
        }
    }

    private fun loadLatestNews() {
        viewModelScope.launch {
            val latestNews = repository.loadLatestNews()
            _latestNewsState.value =
                NewsListLatestState.LatestNews(
                    latestNews = latestNews,
                    latestNewsIsLoading = false
                )
        }
    }

    fun loadNextFeaturedNews() {
        _featuredNewsState.value = NewsListFeaturedState.FeaturedNews(
            featuredNews = repository.featuredNews,
            featuredNewsIsLoading = true
        )
        loadFeaturedNews()
    }

    fun loadNextLatestdNews() {
        _latestNewsState.value = NewsListLatestState.LatestNews(
            latestNews = repository.latestNews,
            latestNewsIsLoading = true
        )
        loadLatestNews()
    }

    fun markFeaturedNews(news: News) {
        markFeaturedNewsUseCase.markNews(news.id)
    }

    fun markLatestNews(news: News) {
        markLatestNewsUseCase.markNews(news.id)
    }

    fun markAllNews() {
        markAllNewsUseCase.markAllNews()
    }

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