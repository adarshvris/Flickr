package com.adarsh.flickrapp.repository.photoSet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.adarsh.flickrapp.api.NetworkState
import com.adarsh.flickrapp.api.PhotoMetaDataDetails
import com.adarsh.flickrapp.api.Status
import com.adarsh.flickrapp.db.dao.PhotoDao
import com.adarsh.flickrapp.repository.Result
import com.adarsh.flickrapp.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoBoundaryCallback @Inject constructor(
    private val photoDao: PhotoDao,
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val method: String,
    private val apiKey: String,
    private val userId: String,
    private val format: String,
    private val noJsonCallback: Int
) : PagedList.BoundaryCallback<PhotoMetaDataDetails>() {

    val networkState = MutableLiveData<NetworkState>()
    var isRequestInProgress: Boolean = false

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        networkState.postValue(NetworkState(Status.LOADING))
        fetchData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PhotoMetaDataDetails) {
        super.onItemAtEndLoaded(itemAtEnd)
        networkState.postValue(NetworkState(Status.LOADING))
        fetchData()
    }

    private fun fetchData() {
        if (isRequestInProgress) {
            return
        }
        CoroutineScope(Dispatchers.IO).launch(getJobErrorHandler()) {

            val count = photoDao.getCount()
            isRequestInProgress = true

            val response = photoRemoteDataSource.getPhotosMetaData(
                (count / NETWORK_PAGE_SIZE) + 1,
                NETWORK_PAGE_SIZE,
                method,
                apiKey,
                userId,
                format,
                noJsonCallback
            )

            if (response.status == Result.Status.SUCCESS) {
                val results = response.data?.photos?.photoList ?: emptyList()
                photoDao.insertAll(results)
                networkState.postValue(NetworkState.LOADED)
                isRequestInProgress = false
            } else if (response.status == Result.Status.ERROR) {
                if (count == 0) {
                    networkState.postValue(NetworkState.error(response.message ?: "unknown err"))
                    postError(response.message!!)
                } else {
                    networkState.postValue(NetworkState.LOADED)
                }
                isRequestInProgress = false
            }
        }
    }

    private fun postError(message: String) {
        Log.e("error", "An error happened: $message")
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }
}