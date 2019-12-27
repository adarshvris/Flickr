package com.adarsh.flickrapp.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.visible() {
    visibility = VISIBLE
}

fun View.gone() {
    visibility = GONE
}