package com.adarsh.flickrapp.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.adarsh.flickrapp.R
import com.adarsh.flickrapp.api.Status
import com.adarsh.flickrapp.databinding.FragmentPhotoGridBinding
import com.adarsh.flickrapp.di.InjectableInterface
import com.adarsh.flickrapp.extensions.gone
import com.adarsh.flickrapp.extensions.injectViewModel
import com.adarsh.flickrapp.extensions.visible
import com.adarsh.flickrapp.ui.adapters.PhotoAdapter
import com.adarsh.flickrapp.ui.vm.PhotoVM
import com.adarsh.flickrapp.utils.*
import kotlinx.android.synthetic.main.fragment_photo_grid.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoGridFragment : Fragment(), InjectableInterface {

    private lateinit var binding: FragmentPhotoGridBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var photoVM: PhotoVM

    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photoAdapter = PhotoAdapter()
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.grid_layout_margin)
        rvPhotos.addItemDecoration(
            GridSpacingItemDecoration(
                SPAN_COUNT,
                spacingInPixels,
                true,
                HEADER_NUM
            )
        )
        binding.rvPhotos.adapter = photoAdapter

        binding.clickOnRetry = View.OnClickListener {
            fetchData()
        }

        photoVM = injectViewModel(viewModelFactory)

        fetchData()

        srlRefresh.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData() {
        val data = photoVM.getPhotosMetaData(
            PHOTO_META_DATA_METHOD,
            API_KEY,
            USER_ID,
            FORMAT,
            NO_JSON_CALLBACK
        )

        data.networkState.observe(this, Observer {
            srlRefresh.isRefreshing = false
            when (it.status) {
                Status.LOADING -> {
                    Log.e("Network", "Status ${it.status}")
                    llRetry.gone()
                    llProgress.visible()
                }

                Status.SUCCESS -> {
                    Log.e("Network", "Status ${it.status}")
                    llRetry.gone()
                    llProgress.gone()
                    rvPhotos.visible()
                }

                Status.FAILED -> {
                    Log.e("Network", "Status ${it.status}")
                    rvPhotos.gone()
                    llProgress.gone()
                    llRetry.visible()
                }
            }
        })

        data.pagedList.observe(this, Observer {
            photoAdapter.submitList(it)
        })
    }

    companion object {
        const val SPAN_COUNT = 3
        const val HEADER_NUM = 0
    }


}
