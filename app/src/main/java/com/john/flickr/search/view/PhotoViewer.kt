package com.john.flickr.search.view

import com.john.flickr.search.model.Photo


interface PhotoViewer {
    fun onPhotosUpdated(photos: List<Photo>)
}