package com.john.flickr

import androidx.annotation.StringRes


interface SnackbarObserver {
    fun onNewMessage(@StringRes messageResourceId: Int)
}