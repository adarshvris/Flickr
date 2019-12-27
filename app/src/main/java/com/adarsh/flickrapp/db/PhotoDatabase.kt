package com.adarsh.flickrapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adarsh.flickrapp.api.PhotoMetaDataDetails
import com.adarsh.flickrapp.db.dao.PhotoDao
import com.adarsh.flickrapp.utils.DATABASE_NAME

@Database(entities = [PhotoMetaDataDetails::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao

    companion object {
        private var photoDatabaseInstance: PhotoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = photoDatabaseInstance ?: synchronized(LOCK) {
            photoDatabaseInstance ?: createDatabase(context).also { photoDatabaseInstance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PhotoDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}