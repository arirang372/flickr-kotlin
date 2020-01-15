package com.john.flickr.data.source

import androidx.databinding.ObservableList
import com.john.flickr.data.source.remote.RemoteDataLoader
import com.john.flickr.search.model.Photo

class PhotoRepository {

    private var dataLoader = RemoteDataLoader()

    fun loadAllPhotos(
        keyword: String,
        callback: PhotosDataSource.LoadPhotosCallback,
        observableList: ObservableList<Photo>
    ) {
        dataLoader.loadAllPhotos(keyword, callback, observableList)
    }
}