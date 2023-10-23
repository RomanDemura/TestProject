package tech.demura.testproject.ui_layer.newsListScreen

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




}