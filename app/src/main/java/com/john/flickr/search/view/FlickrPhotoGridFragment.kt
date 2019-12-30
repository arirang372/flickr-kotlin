package com.john.flickr.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment

class FlickrPhotoGridFragment : Fragment() {
    companion object {
        const val IMAGE_SIZE_KEY = "image_size"
        const val PRELOAD_KEY = "preload"
        const val STATE_POSITION_INDEX = "state_position_index"
        const val THUMBNAIL_KEY = "thumbnail"

        fun newInstance(size: Int, preloadCount: Int, thumbname: Boolean): FlickrPhotoGridFragment {
            var photoGrid = FlickrPhotoGridFragment()
            var args = Bundle()
            args.putInt(IMAGE_SIZE_KEY, size)
            args.putInt(PRELOAD_KEY, preloadCount)
            args.putBoolean(THUMBNAIL_KEY, thumbname)
            photoGrid.arguments = args
            return photoGrid
        }
    }

    var photoSize : Int =


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var args = Preconditions.checkNotNull(arguments)



        return super.onCreateView(inflater, container, savedInstanceState)
    }

}