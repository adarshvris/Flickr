package com.adarsh.flickrapp.repository.photoSet

import com.adarsh.flickrapp.api.ApiInterface
import com.adarsh.flickrapp.api.BaseDataSource
import com.adarsh.flickrapp.api.PhotosResponse
import javax.inject.Inject
import com.adarsh.flickrapp.repository.Result

class PhotoRemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) :
    BaseDataSource() {

    suspend fun getPhotosMetaData(
        perPage: Int,
        page: Int,
        method: String,
        apiKey: String,
        userId: String,
        format: String,
        noJsonCallback: Int
    ): Result<PhotosResponse> {

        return getResult {
            apiInterface.getPhotosMetaData(
                perPage,
                page,
                method,
                apiKey,
                userId,
                format,
                noJsonCallback
            )
        }
    }
}