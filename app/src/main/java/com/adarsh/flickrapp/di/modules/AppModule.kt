package com.adarsh.flickrapp.di.modules

import android.app.Application
import com.adarsh.flickrapp.api.ApiInterface
import com.adarsh.flickrapp.api.ApiInterface.Companion.BASE_URL
import com.adarsh.flickrapp.db.PhotoDatabase
import com.adarsh.flickrapp.repository.photoSet.PhotoRemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideFlickrService(
        okhttpClient: OkHttpClient
    ) = provideService(okhttpClient, ApiInterface::class.java)

    private fun createRetrofit(
        okhttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient, clazz: Class<T>): T {
        return createRetrofit(okhttpClient).create(clazz)
    }

    @Singleton
    @Provides
    fun providesPhotoRemoteDataSource(apiInterface: ApiInterface): PhotoRemoteDataSource =
        PhotoRemoteDataSource(apiInterface)

    @Singleton
    @Provides
    fun providesPhotoDatabase(application: Application) = PhotoDatabase.invoke(application)

    @Singleton
    @Provides
    fun providesPhotoDao(photoDatabase: PhotoDatabase) = photoDatabase.getPhotoDao()
}