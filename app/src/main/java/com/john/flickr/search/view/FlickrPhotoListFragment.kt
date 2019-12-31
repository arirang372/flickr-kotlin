package com.john.flickr.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.john.flickr.databinding.FlickrPhotoListBinding
import com.john.flickr.search.model.Photo
import kotlinx.android.synthetic.main.flickr_photo_list.*


class FlickrPhotoListFragment : Fragment(), PhotoViewer {
    companion object {
        const val PRELOAD_AHEAD_ITEMS = 5
        const val STATE_POSITION_INDEX = "state_position_index"
        const val STATE_POSITION_OFFSET = "state_position_offset"

        fun newInstance(): FlickrPhotoListFragment {
            return FlickrPhotoListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FlickrPhotoListBinding.inflate(inflater, false)
        flickr_photo_list.adapter = FlickrPhotoListAdapter()
        var preloader = RecyclerViewPreloader<Photo>(
            Glide.with(this), flickr_photo_list.adapter as FlickrPhotoListAdapter,
            ViewPreloadSizeProvider<Photo>(),
            PRELOAD_AHEAD_ITEMS
        )
        with(flickr_photo_list) {
            addOnScrollListener(preloader)
            setItemViewCacheSize(0)
            setRecyclerListener { holder ->
                var viewHolder: FlickrPhotoListAdapter.PhotoTileViewHolder =
                    holder as FlickrPhotoListAdapter.PhotoTileViewHolder
                Glide.with(this@FlickrPhotoListFragment)
                    .clear(viewHolder.listItemBinding.photoView)
            }
        }

        with(savedInstanceState)
        {
            var index = this?.getInt(STATE_POSITION_INDEX)
            var offset = this?.getInt(STATE_POSITION_OFFSET)
            if (index != null && offset != null) {
                (flickr_photo_list.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    index,
                    offset
                )
            }
        }
        return binding.root
    }

    override fun onPhotosUpdated(photos: List<Photo>) {
        (flickr_photo_list.adapter as FlickrPhotoListAdapter).setPhotos(photos)
    }
}