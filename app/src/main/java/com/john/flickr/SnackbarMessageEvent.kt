package com.john.flickr

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class SnackbarMessageEvent : SingleLiveEvent<Int>() {
    fun observe(owner: LifecycleOwner, observer: SnackbarObserver) {
        super.observe(owner, Observer()
        {
            it?.let { observer.onNewMessage(it) }
        })
    }
}