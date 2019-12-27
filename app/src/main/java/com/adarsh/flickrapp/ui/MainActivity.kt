package com.adarsh.flickrapp.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.adarsh.flickrapp.R
import com.adarsh.flickrapp.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}
