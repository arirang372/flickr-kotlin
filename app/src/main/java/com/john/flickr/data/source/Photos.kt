package com.john.flickr.data.source

import com.john.flickr.data.Photo


class Photos {
    var page: Int = 0
    var pages: Int = 0
    var perpage: Int = 0
    var total: Int = 0
    var photo: MutableList<Photo> = mutableListOf()
}