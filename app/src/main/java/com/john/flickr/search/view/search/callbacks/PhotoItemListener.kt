package com.john.flickr.search.view.search.callbacks

import com.john.flickr.search.model.Photo

interface PhotoItemListener {
    fun onItemClicked(photo: Photo)
}