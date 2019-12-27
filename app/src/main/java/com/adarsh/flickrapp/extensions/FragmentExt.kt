package com.adarsh.flickrapp.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM: ViewModel> Fragment.injectViewModel(viewModelProvider: ViewModelProvider.Factory): VM {
    return ViewModelProviders.of(this, viewModelProvider).get(VM::class.java)
}