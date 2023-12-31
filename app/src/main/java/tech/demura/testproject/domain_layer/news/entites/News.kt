package tech.demura.testproject.domain_layer.news.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import tech.demura.testproject.R
import java.util.Date

@Parcelize
data class News (
    var id: Int = UNDEFINED_ID,
    val title: String = "Title",
    val text: String = "Text",
    val imageId: Int = R.drawable.ic_launcher_background,
    val imageUrl: String = "",
    var isViewed: Boolean = false,
    var publishedDate: Date = Date()

    ): Parcelable {
    companion object{
        const val UNDEFINED_ID = 0
    }
}
