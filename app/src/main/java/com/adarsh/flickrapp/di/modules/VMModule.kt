package com.adarsh.flickrapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adarsh.flickrapp.di.ViewModelFactory
import com.adarsh.flickrapp.di.ViewModelKey
import com.adarsh.flickrapp.ui.vm.PhotoVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {

    @Binds
    abstract fun bindsViewModelProvider(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PhotoVM::class)
    abstract fun bindsPhotoVM(photoVM: PhotoVM): ViewModel
}