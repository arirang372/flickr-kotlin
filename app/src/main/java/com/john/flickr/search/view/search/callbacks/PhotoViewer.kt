package com.john.flickr.search.view.search.callbacks

import com.john.flickr.search.model.Photo


interface PhotoViewer {
    fun onPhotosUpdated(photos: MutableList<Photo>?)
}