package com.john.flickr.data

import com.john.flickr.search.model.Photo


data class UrlCacheKey(var photo: Photo, var sizeKey: String)
