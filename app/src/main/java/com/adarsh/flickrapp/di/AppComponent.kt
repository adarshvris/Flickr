package com.adarsh.flickrapp.di

import android.app.Application
import com.adarsh.flickrapp.FlickrApplication
import com.adarsh.flickrapp.di.modules.ActivityModule
import com.adarsh.flickrapp.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityModule::class, AppModule::class])
interface AppComponent : AndroidInjector<FlickrApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bind(application: Application): Builder

        fun build(): AppComponent
    }
}