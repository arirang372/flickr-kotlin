package com.john.flickr.search.view.search.callbacks

import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.recyclerview.widget.RecyclerView
import com.john.flickr.R
import com.john.flickr.search.viewmodel.FlickrSearchViewModel

class ElementCallback(viewModel: FlickrSearchViewModel, recyclerView: RecyclerView) :
    SharedElementCallback() {
    private val mViewModel = viewModel
    private val mRecyclerView = recyclerView
    override fun onMapSharedElements(
        names: MutableList<String>?,
        sharedElements: MutableMap<String, View>?
    ) {
        mViewModel?.let {
            var selectedViewHolder =
                mRecyclerView.findViewHolderForAdapterPosition(it.currentImageItemPosition)
            selectedViewHolder?.let {
                sharedElements?.let { it ->
                    it.put(
                        names!![0],
                        selectedViewHolder.itemView.findViewById(R.id.flickr_photo_grid)
                    )
                }
            }
        }
    }
}