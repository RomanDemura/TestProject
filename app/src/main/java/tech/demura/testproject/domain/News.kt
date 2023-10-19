package tech.demura.testproject.domain

import tech.demura.testproject.R

data class News(
    var id: Int = UNDEFINED_ID,
    val title: String = "We are processing your request...",
    val text: String = "Lorem Ipsum...",
    val imageId: Int = R.drawable.ic_launcher_background,
    var isViewed: Boolean = false,
    var publishedDate: Long = System.currentTimeMillis()

    ){
    companion object{
        const val UNDEFINED_ID = 0
    }
}
