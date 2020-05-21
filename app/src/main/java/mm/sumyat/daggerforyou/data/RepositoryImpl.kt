package mm.sumyat.daggerforyou.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import mm.sumyat.daggerforyou.domain.Repository
import mm.sumyat.daggerforyou.network.NetworkService
import mm.sumyat.daggerforyou.network.model.PlayingMoviewsResponse
import mm.sumyat.daggerforyou.storage.Movie
import mm.sumyat.daggerforyou.storage.MovieDao
import mm.sumyat.daggerforyou.util.ViewState
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val service: NetworkService
) : Repository {

    /**
     * Fetch the movie list from database if exist else fetch from web
     * and persist them in the database
     */

    override fun getMovieList(): Flow<ViewState<List<Movie>>> {
        return flow {
            //send loading
            emit(ViewState.loading<List<Movie>>())
            //send movie list from db
            emit(ViewState.success(movieDao.getMovies()))
            //call api
            val response =service.getPlayingMovie()
            //save data in db
            response.movieResults.let {
                movieDao.insertMovies(it.map {
                    Movie(
                        it.id,
                        it.title,
                        it.poster_path,
                        it.vote_average.toString(),
                        it.overview,
                        it.release_date
                    )
                })
            }
            //send latest movie list from db
            emit(ViewState.success(movieDao.getMovies()))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }

}