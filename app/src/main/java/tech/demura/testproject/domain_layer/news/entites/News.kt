package tech.demura.testproject.domain_layer.news.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var id: Int = UNDEFINED_ID,
    val title: String,
    val text: String,
    val imageId: Int,
    var isViewed: Boolean = false,
    var publishedDate: Long = System.currentTimeMillis()

    ): Parcelable {
    companion object{
        const val UNDEFINED_ID = 0
    }
}
