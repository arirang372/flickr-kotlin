package com.john.flickr.data.source

import androidx.lifecycle.MutableLiveData
import com.john.flickr.data.source.remote.RemoteDataLoader
import com.john.flickr.search.model.Photo

class PhotoRepository {

    private var dataLoader = RemoteDataLoader()

    fun loadAllPhotos(
        keyword: String,
        callback: PhotosDataSource.LoadPhotosCallback
    ): MutableLiveData<MutableList<Photo>> {
        return dataLoader.loadAllPhotos(keyword, callback)
    }

    fun getPhoto(id: String, callback: PhotosDataSource.GetPhotoCallback) {

    }

}