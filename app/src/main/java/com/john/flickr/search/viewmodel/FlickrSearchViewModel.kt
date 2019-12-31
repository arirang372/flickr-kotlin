package com.john.flickr.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.john.flickr.data.source.PhotoRepository
import com.john.flickr.data.source.PhotosDataSource
import com.john.flickr.search.model.Photo

class FlickrSearchViewModel(application: Application, repository: PhotoRepository) :
    AndroidViewModel(application), PhotosDataSource.LoadPhotosCallback {
    val mApplication: Application = application
    val mRepository: PhotoRepository = repository

    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    private lateinit var photos: LiveData<MutableList<Photo>>

    fun getPhotos(): LiveData<MutableList<Photo>> {
        return photos
    }

    fun executeSearch(input: String) {
        setDataLoading(true)
        photos = mRepository.loadAllPhotos(input, this)
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