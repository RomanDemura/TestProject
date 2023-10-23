package tech.demura.testproject.ui_layer.newsListScreen

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.demura.testproject.data_layer.NewsRepositoryImpl
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.use_case.*


class NewsListViewModel : ViewModel() {

    private val repository = NewsRepositoryImpl

    lateinit var featuredNewsLD: LiveData<List<News>>
    lateinit var latestNewsLD: LiveData<List<News>>

    private val getAllFeaturedNewsUseCase = getAllFeaturedNewsUseCase(repository)
    private val getAllLatestNewsUseCase = getAllLatestNewsUseCase(repository)
    private val featuredNews = getAllFeaturedNewsUseCase.getAllNews()
    private val latestNews = getAllLatestNewsUseCase.getAllNews()

    private val markFeaturedNewsUseCase = markFeaturedNewsUseCase(repository)
    private val markLatestNewsUseCase = markLatestNewsUseCase(repository)
    private val markAllNewsUseCase = markAllNewsUseCase(repository)

    private val _screenState = MutableLiveData<NewsListScreenState>(NewsListScreenState.Initial)
    val screenState: LiveData<NewsListScreenState> = _screenState

    fun getNews(){
        featuredNewsLD = featuredNews
        latestNewsLD = latestNews

        _screenState.value = NewsListScreenState.NewsList(
            featuredNews = featuredNews,
            latestNews = latestNews
        )
    }

    fun markFeaturedNews(news: News){
        markFeaturedNewsUseCase.markNews(news.id)
    }
    fun markLatestNews(news: News){
        markLatestNewsUseCase.markNews(news.id)
    }
    fun markAllNews(){
        markAllNewsUseCase.markAllNews()
    }

    fun getPublishTime(news: News): String{
        val currentTime = System.currentTimeMillis()
        val publishTime = news.publishedDate
        val diff = currentTime -publishTime
        val minutes = diff / DateUtils.MINUTE_IN_MILLIS
        val hours = diff / DateUtils.HOUR_IN_MILLIS
        val days = diff / DateUtils.DAY_IN_MILLIS
        val month = diff / (DateUtils.DAY_IN_MILLIS * 30)
        val years = diff / (DateUtils.DAY_IN_MILLIS * 365)

        val res = if (years >= 1){
            val check = if (years == 1L){
                "year"
            } else{
                "years"
            }
            "$years $check ago"
        } else if (month >= 1){
            val check = if (month == 1L){
                "month"
            } else{
                "months"
            }
            "$month $check ago"
        } else if (days >= 1){
            val check = if (days == 1L){
                "day"
            } else{
                "days"
            }
            "$days $check ago"
        } else if (hours >= 1){
            val check = if (hours == 1L){
                "hour"
            } else{
                "hours"
            }
            "$hours $check ago"
        } else if (minutes >= 1){
            val check = if (minutes == 1L){
                "minute"
            } else{
                "minutes"
            }
            "$minutes $check ago"
        } else "Now"

        return res
    }




}