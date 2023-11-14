package tech.demura.testproject.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.demura.testproject.ui_layer.newsScreen.NewsViewModel

@Module
interface NewsViewModelModule {
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    @Binds
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}