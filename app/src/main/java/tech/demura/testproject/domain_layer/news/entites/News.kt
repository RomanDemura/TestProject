package tech.demura.testproject.domain_layer.news.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import tech.demura.testproject.R

@Parcelize
data class News(
    var id: Int = UNDEFINED_ID,
    val title: String = "Title",
    val text: String = "Text",
    val imageId: Int = R.drawable.ic_launcher_background,
    val imageUrl: String = "",
    var isViewed: Boolean = false,
    var publishedDate: Long = System.currentTimeMillis()

    ): Parcelable {
    companion object{
        const val UNDEFINED_ID = 0
    }
}
