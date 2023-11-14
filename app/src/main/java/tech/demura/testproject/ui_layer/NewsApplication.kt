package tech.demura.testproject.ui_layer

import android.app.Application
import tech.demura.testproject.di.ApplicationComponent
import tech.demura.testproject.di.DaggerApplicationComponent

class NewsApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}