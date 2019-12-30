package com.john.flickr

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.john.flickr.data.source.PhotoRepository
import com.john.flickr.search.viewmodel.FlickrSearchViewModel

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    private lateinit var mApplication: Application

    constructor(application: Application) : this() {
        mApplication = application
        mRepository = PhotoRepository()
    }

    private lateinit var mRepository: PhotoRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlickrSearchViewModel::class.java)) {
            return FlickrSearchViewModel(mApplication, mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class : " + modelClass.name)
    }

}