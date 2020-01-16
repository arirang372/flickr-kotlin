package com.john.flickr.search.view.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.FixedPreloadSizeProvider
import com.john.flickr.R
import com.john.flickr.SnackbarObserver
import com.john.flickr.databinding.FlickrPhotoGridBinding
import com.john.flickr.search.model.Photo
import com.john.flickr.search.viewmodel.FlickrSearchViewModel
import com.john.flickr.utils.Utils

class FlickrPhotoGridFragment : Fragment() {
    companion object {
        const val IMAGE_SIZE_KEY = "image_size"
        const val PRELOAD_KEY = "preload"
        const val STATE_POSITION_INDEX = "state_position_index"
        const val THUMBNAIL_KEY = "thumbnail"

        fun newInstance(size: Int, preloadCount: Int, thumbname: Boolean): FlickrPhotoGridFragment {
            var photoGrid =
                FlickrPhotoGridFragment()
            var args = Bundle()
            args.putInt(IMAGE_SIZE_KEY, size)
            args.putInt(PRELOAD_KEY, preloadCount)
            args.putBoolean(THUMBNAIL_KEY, thumbname)
            photoGrid.arguments = args
            return photoGrid
        }
    }

    private var viewModel: FlickrSearchViewModel? = null
    lateinit var binding: FlickrPhotoGridBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var args = Preconditions.checkNotNull(arguments)
        var photoSize = args.getInt(IMAGE_SIZE_KEY)
        var thumbnail = args.getBoolean(THUMBNAIL_KEY)
        var preloadKey = args.getInt(PRELOAD_KEY)

        binding = FlickrPhotoGridBinding.inflate(inflater, container, false)
        viewModel = activity?.let { FlickrSearchActivity.obtainViewModel(it) }

        var gridMargin: Int = resources.getDimensionPixelOffset(R.dimen.grid_margin)
        var spanCount: Int = resources.displayMetrics.widthPixels / (photoSize + (2 * gridMargin))
        var flickrPhotoGrid: RecyclerView = binding.root.findViewById(R.id.flickr_photo_grid)
        flickrPhotoGrid.layoutManager = GridLayoutManager(activity, spanCount)
        flickrPhotoGrid.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(gridMargin, gridMargin, gridMargin, gridMargin)
            }
        })
        flickrPhotoGrid.setRecyclerListener { holder ->
            var flickrPhotoGridViewHolder: FlickrPhotoGridAdapter.PhotoViewHolder =
                holder as FlickrPhotoGridAdapter.PhotoViewHolder
            Glide.with(this@FlickrPhotoGridFragment)
                .clear(flickrPhotoGridViewHolder.gridItemBinding.imageView)
        }

        var heightCount = resources.displayMetrics.heightPixels / photoSize
        flickrPhotoGrid.recycledViewPool.setMaxRecycledViews(0, spanCount * heightCount * 2)
        flickrPhotoGrid.setItemViewCacheSize(0)
        flickrPhotoGrid.adapter = FlickrPhotoGridAdapter(photoSize, thumbnail, viewModel!!)

        var preloadSizeProvider: FixedPreloadSizeProvider<Photo> =
            FixedPreloadSizeProvider(photoSize, photoSize)
        var preloader: RecyclerViewPreloader<Photo> = RecyclerViewPreloader<Photo>(
            Glide.with(this),
            flickrPhotoGrid.adapter as FlickrPhotoGridAdapter,
            preloadSizeProvider,
            preloadKey
        )
        flickrPhotoGrid.addOnScrollListener(preloader)
        flickrPhotoGrid.scrollToPosition(savedInstanceState?.getInt(STATE_POSITION_INDEX) ?: 0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setupSnackbar()
    }

    fun setupSnackbar() {
        viewModel?.getSnackbarMessage()?.observe(this, object : SnackbarObserver {
            override fun onNewMessage(message: String) {
                Utils.showSnackBar(view, getString(R.string.searching_for, message))
            }
        })
    }
}