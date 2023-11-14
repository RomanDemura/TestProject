package tech.demura.testproject.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tech.demura.testproject.MainActivity

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun getNewsScreenComponentFactory(): NewsScreenComponent.Factory


    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}