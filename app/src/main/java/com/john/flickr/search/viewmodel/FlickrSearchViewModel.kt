package com.john.flickr.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.john.flickr.data.source.PhotoRepository

class FlickrSearchViewModel(application: Application, repository: PhotoRepository) : AndroidViewModel(application)
{
    val dataLoading : ObservableBoolean = ObservableBoolean(false)


}