package com.adarsh.flickrapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adarsh.flickrapp.R
import com.adarsh.flickrapp.api.PhotoMetaDataDetails
import com.adarsh.flickrapp.databinding.FragmentPhotoFullViewBinding
import com.adarsh.flickrapp.databinding.PhotoHolderLayoutBinding
import com.adarsh.flickrapp.ui.PhotoFullViewFragment
import com.adarsh.flickrapp.ui.PhotoGridFragment
import com.adarsh.flickrapp.ui.PhotoGridFragmentDirections

class PhotoAdapter :
    PagedListAdapter<PhotoMetaDataDetails, PhotoAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PhotoHolderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, createOnClickListener(it)) }
    }

    class ViewHolder(private val binding: PhotoHolderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photoMetaDataDetails: PhotoMetaDataDetails, listener: View.OnClickListener) {
            with(itemView) {
                binding.apply {
                    photoMetaData = photoMetaDataDetails
                    onItemClicked = listener
                }
            }
        }
    }

    private fun createOnClickListener(photoMetaDataDetails: PhotoMetaDataDetails): View.OnClickListener {
        return View.OnClickListener {
            it.findNavController().navigate(
                PhotoGridFragmentDirections.actionPhotoGridFragmentToPhotoFullViewFragment(
                    photoMetaDataDetails
                )
            )
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<PhotoMetaDataDetails>() {

    override fun areItemsTheSame(
        oldItem: PhotoMetaDataDetails,
        newItem: PhotoMetaDataDetails
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PhotoMetaDataDetails,
        newItem: PhotoMetaDataDetails
    ): Boolean {
        return oldItem == newItem
    }
}