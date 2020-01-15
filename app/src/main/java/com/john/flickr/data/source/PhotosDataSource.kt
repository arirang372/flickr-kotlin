package com.john.flickr.data.source


interface PhotosDataSource {

    interface LoadPhotosCallback {
        fun onPhotosLoaded(message : String)

        fun onPhotoNotAvailable()
    }

    interface GetPhotoCallback {
        fun onPhotoLoaded()

        fun onPhotoNotAvailable()
    }

    fun getPhotos(callback: LoadPhotosCallback)

    fun getPhoto(id: String, callback: GetPhotoCallback)
}