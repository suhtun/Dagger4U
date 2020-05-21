package mm.sumyat.daggerforyou.network

import androidx.lifecycle.LiveData
import mm.sumyat.daggerforyou.network.model.PlayingMoviewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("now_playing?page=1")
    suspend fun getPlayingMovie(): PlayingMoviewsResponse
}