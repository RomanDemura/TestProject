package tech.demura.testproject.data_layer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.demura.testproject.R
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository

object NewsRepositoryImpl : NewsRepository {

    val featuredNewsLD = MutableLiveData<List<News>>()
    val featuredNewsSet = sortedSetOf<News>({o1, o2 -> o1.id.compareTo(o2.id)})

    val latestNewsLD = MutableLiveData<List<News>>()
    val latestNewsSet = sortedSetOf<News>({o1, o2 -> o1.id.compareTo(o2.id)})

    init {
        repeat(30) {
            val news = News(
                id = it,
                title = "Featured $it",
                text = "Featured Text $it",
                imageId = R.drawable.code_img
            )
            addFeaturedNews(news = news)
        }
        repeat(30) {
            val news = News(
                id = it,
                title = "Latest $it",
                text = "Latest Text $it",
                imageId = R.drawable.code_img
            )
            addLatestNews(news = news)
        }
    }

    private fun updateFeaturedLD() {
        featuredNewsLD.value = featuredNewsSet.toList()
    }

    private fun updateLatestLD() {
        latestNewsLD.value = latestNewsSet.toList()
    }

    override fun addFeaturedNews(news: News) {
        featuredNewsSet.add(news)
        updateFeaturedLD()
    }

    override fun addLatestNews(news: News) {
        latestNewsSet.add(news)
        updateLatestLD()
    }

    override fun getFeaturedNews(id: Int): News {
        val news = featuredNewsSet.find {
            it.id == id
        } ?: throw RuntimeException("Invalid featured news id: $id")
        return news
    }

    override fun getLatestNews(id: Int): News {
        val news = latestNewsSet.find {
            it.id == id
        } ?: throw RuntimeException("Invalid latest news id: $id")
        return news
    }

    override fun markFeaturedNews(id: Int) {
        var news = getFeaturedNews(id)
        featuredNewsSet.remove(news)
        news = news.copy(isViewed = true)
        addFeaturedNews(news = news)
        updateFeaturedLD()
    }

    override fun markLatestNews(id: Int) {
        var news = getLatestNews(id)
        latestNewsSet.remove(news)
        news = news.copy(isViewed = true)
        addLatestNews(news = news)
        updateLatestLD()
    }

    private fun markAllFeaturedNews() {
        val featuredList = featuredNewsSet.toList()
        featuredList.forEach {
            markFeaturedNews(it.id)
        }
    }

    private fun markAllLatestNews() {
        val latestList = latestNewsSet.toList()
        latestList.forEach {
            markLatestNews(it.id)
        }
    }

    override fun markAllNews() {
        markAllFeaturedNews()
        markAllLatestNews()
    }

    override fun getAllFeaturedNews(): LiveData<List<News>> {
        return featuredNewsLD
    }

    override fun getAllLatestNews(): LiveData<List<News>> {
        return latestNewsLD
    }

}