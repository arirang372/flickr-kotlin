package com.john.flickr

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.john.flickr.data.source.PhotoRepository
import com.john.flickr.search.viewmodel.FlickrDetailsViewModel
import com.john.flickr.search.viewmodel.FlickrSearchViewModel

class ViewModelFactory(application: Application) : ViewModelProvider.NewInstanceFactory() {
    private var mApplication: Application = application
    private var mRepository: PhotoRepository = PhotoRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlickrSearchViewModel::class.java)) {
            return FlickrSearchViewModel(mApplication, mRepository) as T
        } else if (modelClass.isAssignableFrom(FlickrDetailsViewModel::class.java)) {
            return FlickrDetailsViewModel(mApplication, mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory {
            if (instance == null)
                instance = ViewModelFactory(application)
            return instance as ViewModelFactory
        }
    }
}