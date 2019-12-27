package com.adarsh.flickrapp.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adarsh.flickrapp.api.PhotoMetaDataDetails

@Dao
interface PhotoDao {

    @Query("SELECT * FROM PhotoMetaDataDetails")
    fun getAllPhotosDetail(): List<PhotoMetaDataDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photoMetaDataDetailsList: List<PhotoMetaDataDetails>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photoMetaDataDetails: PhotoMetaDataDetails)

    @Query("SELECT * FROM PhotoMetaDataDetails")
    fun getPagedPhotoDetails(): DataSource.Factory<Int, PhotoMetaDataDetails>

    @Query("SELECT COUNT(*) from PhotoMetaDataDetails")
    fun getCount(): Int
}