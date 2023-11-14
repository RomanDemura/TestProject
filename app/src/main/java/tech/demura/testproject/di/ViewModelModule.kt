package tech.demura.testproject.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.demura.testproject.ui_layer.newsListScreen.NewsListViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    @Binds
    fun bindNewsListViewModel(viewModel: NewsListViewModel): ViewModel


}