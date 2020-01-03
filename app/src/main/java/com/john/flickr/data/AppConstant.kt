package com.john.flickr.data

import android.util.LruCache
import android.util.SparseArray
import androidx.core.util.keyIterator
import com.john.flickr.search.model.Photo
import kotlin.math.max


class AppConstant {
    companion object {
        const val MAX_URLS_TO_CACHE = 2000
        val CACHED_URLS: LruCache<UrlCacheKey, String> = LruCache(MAX_URLS_TO_CACHE)
        val EDGE_TO_SIZE_KEY = SparseArray<String>().apply {
            put(75, "s")
            put(100, "t")
            put(150, "q")
            put(240, "m")
            put(320, "n")
            put(640, "z")
            put(1024, "b")
        }

        var SORTED_SIZE_KEYS: List<Int> = mutableListOf()
            get() {
                var keys = mutableListOf(EDGE_TO_SIZE_KEY.size())
                for (e in EDGE_TO_SIZE_KEY.keyIterator()) {
                    keys.add(e)
                }
                keys.sort()
                return keys
            }
        var SQUARE_THUMB_SIZE: Int = SORTED_SIZE_KEYS[0]

        fun getLargerSizeKeys(width: Int, height: Int): List<String> {
            var largestEdge = max(width, height)
            var isFirstLargest = true
            var result = mutableListOf<String>()
            for (s in SORTED_SIZE_KEYS.indices) {
                var edge = SORTED_SIZE_KEYS[s]
                if (largestEdge <= edge) {
                    if (isFirstLargest) {
                        isFirstLargest = false
                    } else {
                        result.add(EDGE_TO_SIZE_KEY[edge])
                    }
                }
            }
            return result
        }

        fun getPhotoUrl(photo: Photo?, sizeKey: String): String {
            var entry = UrlCacheKey(photo!!, sizeKey)
            var result: String?
            result = photo.partialUrl + sizeKey + ".jpg"
            CACHED_URLS.put(entry, result)
            return result
        }

        fun getAlternateUrls(photo: Photo?, width: Int, height: Int): List<String> {
            var result = mutableListOf<String>()
            for (s in getLargerSizeKeys(width, height))
                result.add(getPhotoUrl(photo = photo, sizeKey = s))
            return result
        }

        fun getSizeKey(width: Int, height: Int): String {
            var largestEdge = max(width, height)
            var result = EDGE_TO_SIZE_KEY[SORTED_SIZE_KEYS[SORTED_SIZE_KEYS.size - 1]]
            for (edge in SORTED_SIZE_KEYS.indices) {
                if (largestEdge <= edge) {
                    result = EDGE_TO_SIZE_KEY[edge]
                    break
                }
            }
            return result
        }

        fun getPhotoURL(photo: Photo?, width: Int, height: Int): String {
            return getPhotoUrl(photo, getSizeKey(width, height))
        }
    }


}