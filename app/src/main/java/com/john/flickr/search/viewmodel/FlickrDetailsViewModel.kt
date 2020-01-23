package com.john.flickr.search.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.john.flickr.data.source.PhotoRepository
import com.john.flickr.search.model.Photo

class FlickrDetailsViewModel(application: Application, repository: PhotoRepository) :
    AndroidViewModel(application) {
    private val mRepository: PhotoRepository = repository
    val photos: MutableList<Photo> = mRepository.getPhotos()
    val currentImageItemPosition = mRepository.getCurrentImageItemPosition()

    fun setCurrentImageItemPosition(position: Int) {
        mRepository.setCurrentImageItemPosition(position)
    }
}