package com.john.flickr.search.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.flickr.databinding.FlickrPhotoDetailsFragmentBinding
import com.john.flickr.search.model.Photo

class FlickrPhotoDetailsFragment : Fragment() {
    private lateinit var flickrPhotoDetailsFragmentBinding: FlickrPhotoDetailsFragmentBinding

    companion object {
        private const val ARG_PHOTO = "photo"

        fun newInstance(photo: Photo): FlickrPhotoDetailsFragment {
            var photoDetails = FlickrPhotoDetailsFragment()
            var args = Bundle()
            args.putParcelable(ARG_PHOTO, photo)
            photoDetails.arguments = args
            return photoDetails
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        flickrPhotoDetailsFragmentBinding = FlickrPhotoDetailsFragmentBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        var args: Bundle? = arguments
        var model = args?.getParcelable<Photo>(ARG_PHOTO) as Photo
        flickrPhotoDetailsFragmentBinding.model = model
        flickrPhotoDetailsFragmentBinding.executePendingBindings()

        return flickrPhotoDetailsFragmentBinding.root
    }

}