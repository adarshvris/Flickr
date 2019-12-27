package com.adarsh.flickrapp.repository.photoSet

import androidx.paging.LivePagedListBuilder
import com.adarsh.flickrapp.api.Data
import com.adarsh.flickrapp.api.NetworkState
import com.adarsh.flickrapp.api.PhotoMetaDataDetails
import com.adarsh.flickrapp.db.dao.PhotoDao
import com.adarsh.flickrapp.utils.DATABASE_PAGE_SIZE
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoDao: PhotoDao
) {

    private lateinit var data: Data<PhotoMetaDataDetails>

    fun getPhotosMetaData(
        method: String,
        apiKey: String,
        userId: String,
        format: String,
        noJsonCallback: Int
    ): Data<PhotoMetaDataDetails> {

        val dataSourceFactory = photoDao.getPagedPhotoDetails()
        val photoBoundaryCallback = PhotoBoundaryCallback(
            photoDao,
            photoRemoteDataSource,
            method,
            apiKey,
            userId,
            format,
            noJsonCallback
        )

        val networkState = photoBoundaryCallback.networkState
        networkState.postValue(NetworkState.LOADED)

        val photoData = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(photoBoundaryCallback)
            .build()

        data = Data(photoData, networkState)
        return data
    }

}