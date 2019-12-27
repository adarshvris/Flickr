package com.adarsh.flickrapp.di.modules

import androidx.paging.PagedList
import com.adarsh.flickrapp.BuildConfig
import com.adarsh.flickrapp.db.dao.PhotoDao
import com.adarsh.flickrapp.repository.photoSet.PhotoRemoteDataSource
import com.adarsh.flickrapp.repository.photoSet.PhotoRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun providesPhotoRepository(photoDao: PhotoDao, photoRemoteDataSource: PhotoRemoteDataSource) =
        PhotoRepository(photoRemoteDataSource, photoDao)
}