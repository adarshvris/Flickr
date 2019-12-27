package com.adarsh.flickrapp.di.modules

import com.adarsh.flickrapp.di.FragmentScope
import com.adarsh.flickrapp.ui.PhotoGridFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope(PhotoGridFragment::class)
    @ContributesAndroidInjector
    abstract fun getPhotoGridFragment(): PhotoGridFragment
}