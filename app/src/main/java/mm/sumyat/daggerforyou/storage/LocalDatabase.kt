package mm.sumyat.daggerforyou.storage

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    /**
     * Get movie DAO
     */
    abstract fun movieDao(): MovieDao

    companion object {

        private const val databaseName = "movie-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, LocalDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
    }
}