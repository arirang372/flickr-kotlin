package com.john.flickr.search.model


data class Photo(
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: String,
    var title: String,
    var partialUrl: String,
    var ispublic: Int = 0,
    var isfriend: Int = 0,
    var isfamily: Int = 0
) {
    override fun toString(): String {
        return partialUrl
    }
}