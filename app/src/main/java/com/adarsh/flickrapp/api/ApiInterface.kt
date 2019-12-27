package com.adarsh.flickrapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/rest/"
    }

    @GET(".")
    suspend fun getPhotosMetaData(
        @Query("page") perpage: Int,
        @Query("per_page") page: Int,
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("user_id", encoded = true) userId: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ): Response<PhotosResponse>
}