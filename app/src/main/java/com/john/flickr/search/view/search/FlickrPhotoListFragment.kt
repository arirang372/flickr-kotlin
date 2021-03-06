package com.john.flickr.search.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.john.flickr.R
import com.john.flickr.databinding.FlickrPhotoListBinding
import com.john.flickr.search.model.Photo
import com.john.flickr.search.viewmodel.FlickrSearchViewModel


class FlickrPhotoListFragment : Fragment() {
    companion object {
        const val PRELOAD_AHEAD_ITEMS = 5
        const val STATE_POSITION_INDEX = "state_position_index"
        const val STATE_POSITION_OFFSET = "state_position_offset"

        fun newInstance(): FlickrPhotoListFragment {
            return FlickrPhotoListFragment()
        }
    }

    lateinit var binding: FlickrPhotoListBinding
    var viewModel: FlickrSearchViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FlickrPhotoListBinding.inflate(inflater, container, false)
        viewModel = activity?.let { FlickrSearchActivity.obtainViewModel(it) }
        var flickrPhotoList: RecyclerView = binding.root.findViewById(R.id.flickr_photo_list)
        flickrPhotoList.layoutManager = LinearLayoutManager(context)
        flickrPhotoList.adapter = FlickrPhotoListAdapter(viewModel!!)
        var preloader = RecyclerViewPreloader<Photo>(
            Glide.with(this), flickrPhotoList.adapter as FlickrPhotoListAdapter,
            ViewPreloadSizeProvider<Photo>(),
            PRELOAD_AHEAD_ITEMS
        )
        with(flickrPhotoList) {
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
                (flickrPhotoList.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    index,
                    offset
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}