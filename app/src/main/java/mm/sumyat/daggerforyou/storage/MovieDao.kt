package mm.sumyat.daggerforyou.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Defines access layer to movie table
 */
@Dao
interface MovieDao {

    /**
     * Insert movie into the table
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<Movie>): List<Long>

    @Query("DELETE FROM movie_table")
    suspend fun clearAllMovie()

    /**
     * Get all the movie from table
     */
    @Query("SELECT * FROM movie_table")
    suspend fun getMovies(): List<Movie>
}