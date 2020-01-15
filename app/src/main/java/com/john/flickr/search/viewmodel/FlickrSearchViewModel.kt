package com.john.flickr.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.john.flickr.data.source.PhotoRepository
import com.john.flickr.data.source.PhotosDataSource
import com.john.flickr.search.model.Photo

class FlickrSearchViewModel(application: Application, repository: PhotoRepository) :
    AndroidViewModel(application), PhotosDataSource.LoadPhotosCallback {
    private val mRepository: PhotoRepository = repository
    val photos: ObservableList<Photo> = ObservableArrayList<Photo>()
    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    fun executeSearch(input: String) {
        setDataLoading(true)
        mRepository.loadAllPhotos(input, this, photos)
    }

    private fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    override fun onPhotosLoaded(photos: List<Photo>) {
        setDataLoading(false)
    }

    override fun onPhotoNotAvailable() {
        setDataLoading(false)
    }
}