package mm.sumyat.daggerforyou.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import mm.sumyat.daggerforyou.ui.screen.MovieFragment

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovie(): MovieFragment

}