package mm.sumyat.daggerforyou.storage

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("poster_path")
    val poster_path: String?,
    @field:SerializedName("vote_average")
    val vote_average: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("release_date")
    val release_date: String
)