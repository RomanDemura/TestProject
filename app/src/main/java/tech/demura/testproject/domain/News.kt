package tech.demura.testproject.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import tech.demura.testproject.R

@Parcelize
data class News(
    var id: Int = UNDEFINED_ID,
    val title: String = "We are processing your request...",
    val text: String = "Lorem Ipsum...",
    val imageId: Int = R.drawable.ic_launcher_background,
    var isViewed: Boolean = false,
    var publishedDate: Long = System.currentTimeMillis()

    ): Parcelable {
    companion object{

        const val UNDEFINED_ID = 0
    }
}
