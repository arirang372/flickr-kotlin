package com.john.flickr.search.view

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
import com.john.flickr.databinding.FlickrPhotoGridBinding
import com.john.flickr.search.model.Photo
import kotlinx.android.synthetic.main.flickr_photo_grid.*

class FlickrPhotoGridFragment : Fragment(), PhotoViewer {
    companion object {
        const val IMAGE_SIZE_KEY = "image_size"
        const val PRELOAD_KEY = "preload"
        const val STATE_POSITION_INDEX = "state_position_index"
        const val THUMBNAIL_KEY = "thumbnail"

        fun newInstance(size: Int, preloadCount: Int, thumbname: Boolean): FlickrPhotoGridFragment {
            var photoGrid = FlickrPhotoGridFragment()
            var args = Bundle()
            args.putInt(IMAGE_SIZE_KEY, size)
            args.putInt(PRELOAD_KEY, preloadCount)
            args.putBoolean(THUMBNAIL_KEY, thumbname)
            photoGrid.arguments = args
            return photoGrid
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var args = Preconditions.checkNotNull(arguments)
        var photoSize = args.getInt(IMAGE_SIZE_KEY)
        var thumbnail = args.getBoolean(THUMBNAIL_KEY)
        var preloadKey = args.getInt(PRELOAD_KEY)

        var binding: FlickrPhotoGridBinding = FlickrPhotoGridBinding.inflate(inflater, false)
        var gridMargin: Int = resources.getDimensionPixelOffset(R.dimen.grid_margin)
        var spanCount: Int = resources.displayMetrics.widthPixels / (photoSize + (2 * gridMargin))
        flickr_photo_grid.layoutManager = GridLayoutManager(activity, spanCount)
        flickr_photo_grid.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(gridMargin, gridMargin, gridMargin, gridMargin)
            }
        })
        flickr_photo_grid.setRecyclerListener { holder ->
            var photoViewHolder: PhotoAdapter.PhotoViewHolder =
                holder as PhotoAdapter.PhotoViewHolder
            Glide.with(this@FlickrPhotoGridFragment)
                .clear(photoViewHolder.gridItemBinding.imageView)
        }

        var heightCount = resources.displayMetrics.heightPixels / photoSize
        flickr_photo_grid.recycledViewPool.setMaxRecycledViews(0, spanCount * heightCount * 2)
        flickr_photo_grid.setItemViewCacheSize(0)
        flickr_photo_grid.adapter = PhotoAdapter(photoSize, thumbnail)

        var preloadSizeProvider: FixedPreloadSizeProvider<Photo> =
            FixedPreloadSizeProvider(photoSize, photoSize)
        var preloader: RecyclerViewPreloader<Photo> = RecyclerViewPreloader<Photo>(
            Glide.with(this),
            flickr_photo_grid.adapter as PhotoAdapter,
            preloadSizeProvider,
            preloadKey
        )
        flickr_photo_grid.addOnScrollListener(preloader)
        flickr_photo_grid.scrollToPosition(savedInstanceState?.getInt(STATE_POSITION_INDEX) ?: 0)
        return binding.root
    }

    override fun onPhotosUpdated(photos: List<Photo>) {
        (flickr_photo_grid.adapter as PhotoAdapter).setPhotos(photos)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            STATE_POSITION_INDEX,
            (flickr_photo_grid.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
        )
    }
}