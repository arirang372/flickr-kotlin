package com.john.flickr.data.source

import com.john.flickr.search.model.Photo


interface PhotosDataSource {

    interface LoadPhotosCallback {
        fun onPhotosLoaded(photos: List<Photo>)

        fun onPhotoNotAvailable()
    }

    interface GetPhotoCallback {
        fun onPhotoLoaded(photo: Photo)

        fun onPhotoNotAvailable()
    }

    fun getPhotos(callback: LoadPhotosCallback)

    fun getPhoto(id: String, callback: GetPhotoCallback)
}