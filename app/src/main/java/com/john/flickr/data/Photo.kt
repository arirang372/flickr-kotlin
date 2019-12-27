package com.john.flickr.data


class Photo {
    lateinit var id: String
    lateinit var owner: String
    lateinit var secret: String
    lateinit var server: String
    lateinit var farm: String
    lateinit var title: String
    var partialUrl: String? = null
    var ispublic: Int = 0
    var isfriend: Int = 0
    var isfamily: Int = 0
}