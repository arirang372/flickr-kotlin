package com.john.flickr.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import com.john.flickr.SingleLiveEvent
import com.john.flickr.SnackbarMessageEvent
import com.john.flickr.data.source.PhotoRepository
import com.john.flickr.data.source.PhotosDataSource
import com.john.flickr.search.model.Photo

class FlickrSearchViewModel(application: Application, repository: PhotoRepository) :
    AndroidViewModel(application), PhotosDataSource.LoadPhotosCallback {
    private val mRepository: PhotoRepository = repository
    val photos: ObservableList<Photo> = ObservableArrayList<Photo>()
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    private val mSnackbarText = SnackbarMessageEvent()
    val mOpenTaskEvent: SingleLiveEvent<Pair<Int, String>> = SingleLiveEvent()

    fun executeSearch(input: String) {
        setDataLoading(true)
        mRepository.loadAllPhotos(input, this, photos)
    }

    private fun setDataLoading(isLoading: Boolean) {
        dataLoading.set(isLoading)
    }

    override fun onPhotosLoaded(message: String) {
        setDataLoading(false)
        mSnackbarText.setValue(message)
    }

    override fun onPhotoNotAvailable() {
        setDataLoading(false)
    }

    fun getSnackbarMessage(): SnackbarMessageEvent {
        return mSnackbarText
    }

    fun getOpenTaskEvent(): SingleLiveEvent<Pair<Int, String>> {
        return mOpenTaskEvent
    }
}