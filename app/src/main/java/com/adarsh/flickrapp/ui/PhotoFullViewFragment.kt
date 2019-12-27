package com.adarsh.flickrapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.adarsh.flickrapp.databinding.FragmentPhotoFullViewBinding

class PhotoFullViewFragment : Fragment() {

    private val argument: PhotoGridFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPhotoFullViewBinding.inflate(inflater, container, false)
        binding.photoMetaDataDeatsils = argument.photoMetaDataDetails
        return binding.root
    }


}
