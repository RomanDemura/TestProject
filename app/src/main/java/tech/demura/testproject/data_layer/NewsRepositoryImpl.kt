package tech.demura.testproject.data_layer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.demura.testproject.R
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository

object NewsRepositoryImpl : NewsRepository {
    private const val KEY_FEATURED = "featured"
    private const val KEY_LATEST = "latest"

    val featuredNewsLD = MutableLiveData<List<News>>()
    val featuredNewsList = mutableListOf<News>()

    val latestNewsLD = MutableLiveData<List<News>>()
    val latestNewsList = mutableListOf<News>()

    init {
        repeat(30) {
            val news = News(
                id = it,
                title = "Featured $it",
                text = "Featured Text $it",
                imageId = R.drawable.code_img
            )
            addNews(news = news, key = KEY_FEATURED)
        }
        repeat(30) {
            val news = News(
                id = it,
                title = "Latest $it",
                text = "Latest Text $it",
                imageId = R.drawable.code_img
            )
            addNews(news = news, key = KEY_LATEST)
        }
        updateLD()
    }

    private fun updateLD() {
        featuredNewsLD.value = featuredNewsList
        latestNewsLD.value = latestNewsList
    }

    override fun addNews(news: News, key: String) {
        when (key) {
            KEY_FEATURED -> {
                featuredNewsList.add(news)
            }
            KEY_LATEST -> {
                latestNewsList.add(news)
            }
        }
    }

    override fun getNews(id: Int): News {
        TODO("Not yet implemented")
    }

    override fun getFeaturedNews(): LiveData<List<News>> {
        return featuredNewsLD
    }

    override fun getLatestNews(): LiveData<List<News>> {
        return latestNewsLD
    }

}