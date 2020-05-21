package mm.sumyat.daggerforyou.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import mm.sumyat.daggerforyou.di.base.MovieViewModelFactory
import mm.sumyat.daggerforyou.di.base.ViewModelKey
import mm.sumyat.daggerforyou.ui.screen.MovieViewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel
}