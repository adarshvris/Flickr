package com.adarsh.flickrapp.ui.vm

import androidx.lifecycle.ViewModel
import com.adarsh.flickrapp.api.ApiInterface
import com.adarsh.flickrapp.api.Data
import com.adarsh.flickrapp.api.PhotoMetaDataDetails
import com.adarsh.flickrapp.repository.photoSet.PhotoRepository
import javax.inject.Inject

class PhotoVM @Inject constructor(private val photoRepository: PhotoRepository) : ViewModel() {


    fun getPhotosMetaData(
        method: String,
        apiKey: String,
        userId: String,
        format: String,
        noJsonCallback: Int
    ) : Data<PhotoMetaDataDetails>{
        return photoRepository.getPhotosMetaData(method, apiKey, userId, format, noJsonCallback)
    }
}