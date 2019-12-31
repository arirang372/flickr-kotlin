package com.john.flickr.search.view

import com.john.flickr.search.model.Photo

interface PhotoListItemListener {
    fun onItemClicked(photo: Photo)
}