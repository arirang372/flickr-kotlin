package com.john.flickr

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(application: Application) : ViewModelProvider.NewInstanceFactory() {
    @Volatile var INSTANCE: ViewModelFactory? = null
        get() {
            if (INSTANCE == null)
                return getInstance(application = mApplication)
            return INSTANCE
        }
    private var mApplication: Application = application
    fun getInstance(application: Application): ViewModelFactory {
        return ViewModelFactory(application)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //if(modelClass.isAssignableFrom())

        return super.create(modelClass)
    }
}