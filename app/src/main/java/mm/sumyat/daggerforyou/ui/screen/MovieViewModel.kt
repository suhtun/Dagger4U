package mm.sumyat.daggerforyou.ui.screen

import androidx.lifecycle.*
import mm.sumyat.daggerforyou.domain.Repository
import mm.sumyat.daggerforyou.storage.Movie
import mm.sumyat.daggerforyou.util.AbsentLiveData
import mm.sumyat.daggerforyou.util.ViewState
import javax.inject.Inject

class MovieViewModel @Inject constructor(repository: Repository):ViewModel(){

    //kind of action to call repository
    private val _callRepo = MutableLiveData<String>()
    //listen everytime data changes insdie _call
    val callRepo: LiveData<String> = _callRepo

    val results : LiveData<ViewState<List<Movie>>> = Transformations
        .switchMap(callRepo) { search ->
            if (search.isNullOrBlank()) {
                AbsentLiveData.create()
            } else {
                repository.getMovieList().asLiveData()
            }
        }

    init {
        _callRepo.value = "init"
    }

    fun refreshCall(){
        _callRepo.value = "refresh"
    }
}