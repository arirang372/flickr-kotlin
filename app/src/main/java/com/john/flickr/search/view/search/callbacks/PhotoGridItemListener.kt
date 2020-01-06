package com.john.flickr.search.view.search.callbacks

import com.john.flickr.search.model.Photo

interface PhotoGridItemListener {
    fun onItemClicked(photo: Photo)
}