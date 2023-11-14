package tech.demura.testproject.di

import dagger.BindsInstance
import dagger.Subcomponent
import tech.demura.testproject.ui_layer.ViewModelFactory

@Subcomponent(modules = [NewsViewModelModule::class])
interface NewsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @idQualifier newsId: Int
        ): NewsScreenComponent
    }
}