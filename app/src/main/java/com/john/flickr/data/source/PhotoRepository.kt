package com.john.flickr.data.source

import androidx.databinding.ObservableList
import com.john.flickr.data.source.remote.RemoteDataLoader
import com.john.flickr.search.model.Photo

class PhotoRepository {

    private var dataLoader = RemoteDataLoader()
    private lateinit var photos: MutableList<Photo>
    private var currentPosition: Int = 0
    fun loadAllPhotos(
        keyword: String,
        callback: PhotosDataSource.LoadPhotosCallback,
        observableList: ObservableList<Photo>
    ) {
        photos = dataLoader.loadAllPhotos(keyword, callback, observableList)
    }

    fun getPhotos(): MutableList<Photo> {
        return photos
    }

    fun setCurrentImageItemPosition(position: Int) {
        currentPosition = position
    }

    fun getCurrentImageItemPosition(): Int {
        return currentPosition
    }
}