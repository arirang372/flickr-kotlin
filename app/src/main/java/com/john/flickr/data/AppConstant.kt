package com.john.flickr.data

import android.util.SparseArray
import androidx.core.util.keyIterator


class AppConstant {
    companion object
    {
        val EDGE_TO_SIZE_KEY = SparseArray<String>().apply {
            put(75, "s")
            put(100, "t")
            put(150, "q")
            put(240, "m")
            put(320, "n")
            put(640, "z")
            put(1024, "b")
        }

        var SORTED_SIZE_KEYS : List<Int> = mutableListOf()
            get()
            {
                var keys = mutableListOf(EDGE_TO_SIZE_KEY.size())
                for (e in EDGE_TO_SIZE_KEY.keyIterator()) {
                    keys.add(e)
                }
                keys.sort()
                return keys
            }
         var SQUARE_THUMB_SIZE : Int = SORTED_SIZE_KEYS[0]
    }


}