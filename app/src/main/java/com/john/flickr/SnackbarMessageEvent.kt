package com.john.flickr

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class SnackbarMessageEvent : SingleLiveEvent<String>() {
    fun observe(owner: LifecycleOwner, observer: SnackbarObserver) {
        super.observe(owner, Observer()
        {
            it?.let { observer.onNewMessage(it) }
        })
    }
}