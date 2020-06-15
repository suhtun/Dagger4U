package mm.sumyat.daggerforyou.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import mm.sumyat.daggerforyou.BuildConfig
import mm.sumyat.daggerforyou.data.RepositoryImpl
import mm.sumyat.daggerforyou.domain.Repository
import mm.sumyat.daggerforyou.network.NetworkService
import mm.sumyat.daggerforyou.network.NetworkServiceFactory
import mm.sumyat.daggerforyou.storage.LocalDatabase
import mm.sumyat.daggerforyou.storage.MovieDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideService(): NetworkService {
        return NetworkServiceFactory.makeNetworkService(BuildConfig.DEBUG)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): LocalDatabase = LocalDatabase.buildDefault(app)

    @Singleton
    @Provides
    fun provideUserDao(db: LocalDatabase): MovieDao = db.movieDao()

    @Singleton
    @Provides
    internal fun provideRepository(dao: MovieDao, service: NetworkService): Repository {
        return RepositoryImpl(dao, service)
    }

}