package mm.sumyat.daggerforyou.domain

import kotlinx.coroutines.flow.Flow
import mm.sumyat.daggerforyou.storage.Movie
import mm.sumyat.daggerforyou.util.ViewState

interface Repository {
    fun getMovieList() : Flow<ViewState<List<Movie>>>
}