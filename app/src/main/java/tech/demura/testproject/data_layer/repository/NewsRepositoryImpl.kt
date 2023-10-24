package tech.demura.testproject.data_layer.repository

import androidx.lifecycle.LiveData
import tech.demura.testproject.R
import tech.demura.testproject.data_layer.cat_fact_api.mapper.CatFactMapper
import tech.demura.testproject.data_layer.cat_fact_api.network.CatFactApiFactory
import tech.demura.testproject.data_layer.cat_image_api.mapper.CatImageMapper
import tech.demura.testproject.data_layer.cat_image_api.network.CatImageApiFactory
import tech.demura.testproject.domain_layer.news.entites.News
import tech.demura.testproject.domain_layer.news.repository.NewsRepository
import kotlin.random.Random

object NewsRepositoryImpl : NewsRepository {
//    val options = TranslatorOptions.Builder()
//        .setSourceLanguage(TranslateLanguage.ENGLISH)
//        .setTargetLanguage(TranslateLanguage.RUSSIAN)
//        .build()
//    val englishRussianTranslator = Translation.getClient(options)

    private val catFactApiService = CatFactApiFactory.apiService
    private val catFactMapper = CatFactMapper()

    private val catImageApiService = CatImageApiFactory.apiService
    private val catImageMapper = CatImageMapper()

    private var featuredNewsAutoIncrement = 0
    private var latestNewsAutoIncrement = 0

    private val _featuredNews = mutableListOf<News>()
    val featuredNews: List<News> = _featuredNews

    private val _latestNewsList = mutableListOf<News>()
    val latestNews: List<News> = _latestNewsList

    suspend fun loadCatFacts(): List<News> {
        val news = getRandomCatFact()
        addFeaturedNews(news = news)
        return featuredNews
    }

    suspend fun getRandomCatFact(): News {
        val catFactResponse = catFactApiService.getRandomCatFact()
        val catImageResponse = catImageApiService.getRandomCatImage()

        val catImageUrl = catImageMapper.mapResponseToUrl(catImageResponse)

        val news = catFactMapper.mapResponseToNews(catFactResponse).copy(
            id = featuredNewsAutoIncrement++,
            imageId = getRandomImage(),
            imageUrl = catImageUrl
        )
        return news
    }

    suspend fun loadLatestNews(): List<News> {
        val news = News(
            id = latestNewsAutoIncrement++,
            title = getRandomTitle(),
            text = getRandomText(),
            imageId = getRandomImage(),
            publishedDate = getPublishDate()
        )
        addLatestNews(news = news)
        return latestNews
    }

    override fun addFeaturedNews(news: News) {
        _featuredNews.add(news)
    }

    override fun addLatestNews(news: News) {
        _latestNewsList.add(news)
    }


    override fun getFeaturedNews(id: Int): News {
        val news = _featuredNews.find {
            it.id == id
        } ?: throw RuntimeException("Invalid latest news id: $id")
        return news
    }

    override fun getLatestNews(id: Int): News {
        val news = _latestNewsList.find {
            it.id == id
        } ?: throw RuntimeException("Invalid latest news id: $id")
        return news
    }

    override fun markFeaturedNews(id: Int) {
        var news = getFeaturedNews(id)
        _featuredNews.remove(news)
        news = news.copy(isViewed = true)
        addFeaturedNews(news = news)
    }

    override fun markLatestNews(id: Int) {
        var news = getLatestNews(id)
        _latestNewsList.remove(news)
        news = news.copy(isViewed = true)
        addLatestNews(news = news)
    }

    private fun markAllFeaturedNews() {
        val featuredList = featuredNews
        featuredList.forEach {
            markFeaturedNews(it.id)
        }
    }

    private fun markAllLatestNews() {
        val latestList = latestNews
        latestList.forEach {
            markLatestNews(it.id)
        }
    }

    override fun markAllNews() {
        markAllFeaturedNews()
        markAllLatestNews()
    }

    override fun getAllFeaturedNews(): LiveData<List<News>> {
        TODO("Not yet implemented")
    }

    override fun getAllLatestNews(): LiveData<List<News>> {
        TODO("Not yet implemented")
    }


    private fun getRandomImage(): Int {
        return when (Random.nextInt(0, 7)) {
            0 -> R.drawable.img_0
            1 -> R.drawable.img_1
            2 -> R.drawable.img_2
            3 -> R.drawable.img_3
            4 -> R.drawable.img_4
            5 -> R.drawable.img_5
            6 -> R.drawable.img_6
            else -> R.drawable.ic_launcher_background
        }
    }

    private fun getPublishDate(): Long {
        return System.currentTimeMillis() - (10000 * latestNewsAutoIncrement)
    }

    private fun getRandomTitle(): String {
        return when (Random.nextInt(0, 7)) {
            0 -> "Nullam ac lacus eget justo vulputate tempor."
            1 -> "Cras blandit nibh."
            2 -> "Duis lacinia vel lectus porta sodales. Vestibulum ut malesuada metus."
            3 -> "Sed eu nibh sodales, elementum eros in, laoreet nibh. Pellentesque eget congue enim. Vestibulum aliquam."
            4 -> "Vivamus venenatis."
            5 -> "In gravida lacus eget."
            6 -> "Maecenas aliquam purus a justo mattis, pharetra pellentesque."
            else -> "Lorem Ipsum.."
        }
    }

    private fun getRandomText(): String {
        return when (Random.nextInt(0, 2)) {
            0 -> "Suspendisse scelerisque arcu nibh, a viverra velit venenatis vulputate. Quisque a ipsum rutrum, venenatis mi nec, hendrerit nisi. Nullam magna magna, tempor id tellus id, euismod dictum sem. Nunc at pulvinar velit. Integer et vulputate nibh. Pellentesque sodales non erat fermentum accumsan. Quisque sed risus tincidunt, consectetur sapien eu, sodales odio. Sed varius, ex quis dictum finibus, orci justo laoreet libero, ac venenatis ligula dui in ex. Praesent varius venenatis nunc sit amet maximus. Vivamus nec leo sed purus egestas sodales.\n" +
                    "\n" +
                    "Quisque nec lectus ante. Maecenas tempor nisi sodales tempor vulputate. Maecenas consectetur erat sed elit dapibus semper. Maecenas sed rutrum odio, a viverra nibh. In id ipsum non sapien mattis rutrum vitae sit amet enim. Fusce commodo dapibus augue, in dapibus nunc consectetur eget. Morbi volutpat nibh ac malesuada efficitur. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent quis tempus elit.\n" +
                    "\n" +
                    "Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec tristique lectus est, ac ullamcorper tortor malesuada a. Sed cursus massa non nulla ultrices, ac malesuada lectus vulputate. Sed sollicitudin at ligula sed dapibus. Curabitur quis posuere leo. Nunc id urna ornare, hendrerit turpis imperdiet, iaculis dui. Suspendisse potenti. Vivamus interdum a felis ut tristique. Mauris ac aliquet magna, et suscipit nisi. Curabitur interdum, mauris id dapibus accumsan, enim nisi eleifend quam, et gravida purus magna pretium mauris. Nulla nec lorem est. Suspendisse libero metus, placerat eu ante non, mollis pharetra est. Mauris et fringilla tortor. Curabitur vehicula mi lorem. Ut egestas pretium nunc, sodales sodales quam pretium at.\n" +
                    "\n" +
                    "Cras vitae eros in purus molestie hendrerit vitae sit amet libero. Donec id neque non felis posuere placerat vel quis dolor. Donec eget leo in ante rhoncus dignissim eu non tortor. Donec posuere turpis maximus scelerisque mattis. Fusce eget enim nec neque ornare porttitor. Vestibulum tincidunt nisl laoreet leo vestibulum, quis fermentum mauris aliquam. Donec pharetra neque sit amet consequat lacinia.\n" +
                    "\n" +
                    "Suspendisse volutpat ultrices congue. Integer ullamcorper quam et nunc semper placerat. Vivamus vitae mauris vel dolor aliquet euismod. Fusce in nulla rutrum, aliquet velit at, viverra leo. In ante urna, interdum a turpis ac, vehicula ultrices odio. Phasellus sagittis id leo vitae pulvinar. Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                    "\n" +
                    "Sed laoreet tortor sit amet mauris tincidunt dictum. Nulla urna mi, maximus sed molestie at, efficitur at odio. Nunc vitae viverra erat. Etiam maximus diam eget porta tristique. Maecenas in faucibus erat. Maecenas ut sollicitudin nibh, eget vehicula nisi. Vestibulum non lacus vel eros porta auctor nec sed est. Pellentesque in tincidunt elit. Morbi nec enim leo. Phasellus finibus mi vel aliquam imperdiet. Donec ut velit id diam dictum pretium. In hac habitasse platea dictumst. Nulla euismod neque ut augue commodo consequat. Nulla facilisi. Donec semper ac tortor at faucibus. In metus ligula, porta at accumsan vitae, finibus ut arcu.\n" +
                    "\n" +
                    "Donec sit amet augue id nibh euismod condimentum eu id libero. Nam tincidunt eleifend nisl, eget commodo augue tristique at. Maecenas suscipit, mi sed consequat commodo, nunc massa venenatis nibh, pharetra mattis erat tortor quis urna. Nunc urna sem, pretium ac dui nec, tincidunt condimentum nibh. Cras sit amet varius neque. Mauris efficitur porta pulvinar. Vestibulum varius odio enim, et mattis nulla tincidunt eu. Etiam tincidunt nisl quis risus congue vestibulum. Phasellus accumsan tincidunt nisi, sit amet elementum ligula.\n" +
                    "\n" +
                    "Quisque facilisis fringilla massa, sed gravida erat pulvinar quis. In convallis mattis mauris vitae vehicula. Vestibulum ac varius sem, ut ultrices massa. Donec nec finibus erat. Quisque non odio at elit porttitor gravida. Fusce scelerisque turpis non egestas malesuada. In vel elit ut est tempor luctus. Phasellus dui urna, faucibus a justo sed, dapibus condimentum mauris. Proin ut sagittis lectus, sit amet tempor eros. Curabitur mi augue, lacinia ut nulla vitae, scelerisque elementum ligula. Pellentesque auctor mauris lacinia sollicitudin sagittis."
            1 -> "Mauris molestie lacus vel neque dapibus, eu eleifend neque consequat. Cras scelerisque lacinia congue. Maecenas luctus sagittis enim sit amet bibendum. Nam eleifend eleifend dolor, aliquet consectetur nisi. Duis commodo diam dolor, convallis cursus sapien bibendum volutpat. Morbi tempus turpis ac consectetur finibus. Aliquam convallis pretium sapien ut luctus. Donec eu accumsan nibh. Mauris eu magna sed neque hendrerit sodales vitae et metus. Phasellus lacinia, lorem vitae pulvinar ultricies, felis justo placerat arcu, ac faucibus velit odio id justo.\n" +
                    "\n" +
                    "Donec lacus metus, condimentum eu purus ac, imperdiet pulvinar enim. Sed a tortor eget augue pretium bibendum vitae non velit. Nunc a nisi turpis. Vestibulum sapien nisi, elementum id eros in, auctor tempus ante. Duis rhoncus viverra elit, in pulvinar lacus blandit a. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Duis quam est, sollicitudin nec dui in, dapibus tempus sapien. Quisque non vehicula lacus. Aenean at efficitur nulla. Praesent felis felis, tincidunt ut dolor sagittis, hendrerit fermentum augue. Pellentesque non gravida nisl. Duis sagittis ex mi, et hendrerit tortor aliquam vel. Quisque efficitur ultrices enim ut varius. Phasellus dignissim eros eget diam lacinia consectetur id et leo."
            else -> "Lorem Ipsum.."
        }
    }

}