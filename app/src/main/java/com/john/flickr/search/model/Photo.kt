package com.john.flickr.search.model


data class Photo(
    var id: String,
    var owner: String,
    var title: String,
    var server: String,
    var farm: String,
    var secret: String,
    var partialUrl: String? = null




)