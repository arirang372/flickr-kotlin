package com.john.flickr


interface SnackbarObserver {
    fun onNewMessage(message: String)
}