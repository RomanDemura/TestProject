package tech.demura.testproject.ui_layer.newsListScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.demura.testproject.data_layer.NewsRepositoryImpl
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.use_case.getFeaturedNewsUseCase
import tech.demura.testproject.domain_layer.news.use_case.getLatestNewsUseCase

class NewsListViewModel : ViewModel() {

    private val repository = NewsRepositoryImpl

    private val getFeaturedNewsUseCase = getFeaturedNewsUseCase(repository)
    private val getLatestNewsUseCase = getLatestNewsUseCase(repository)

    lateinit var featuredNewsLD: LiveData<List<News>>
    lateinit var latestNewsLD: LiveData<List<News>>

    private val _screenState = MutableLiveData<NewsListScreenState>(NewsListScreenState.Initial)
    val screenState: LiveData<NewsListScreenState> = _screenState

    fun getNews(){
        featuredNewsLD = getFeaturedNewsUseCase.getFeaturedNews()
        latestNewsLD = getLatestNewsUseCase.getLatestNews()
        if (featuredNewsLD.value.isNullOrEmpty()) return
        if (latestNewsLD.value.isNullOrEmpty()) return

        _screenState.value = NewsListScreenState.NewsList(
            featuredNews = featuredNewsLD.value!!,
            latestNews = latestNewsLD.value!!
        )
    }



}