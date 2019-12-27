package com.adarsh.flickrapp

import android.app.Activity
import com.adarsh.flickrapp.di.AppInjector
import com.adarsh.flickrapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class FlickrApplication : DaggerApplication() {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().bind(this).build()

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = androidInjector

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }
}