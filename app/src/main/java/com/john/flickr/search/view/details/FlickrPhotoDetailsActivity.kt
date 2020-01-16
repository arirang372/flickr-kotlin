package com.john.flickr.search.view.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.john.flickr.R
import com.john.flickr.databinding.FlickrPhotoDetailsActivityBinding
import com.john.flickr.search.model.Photo


class FlickrPhotoDetailsActivity : AppCompatActivity() {
    private lateinit var flickrPhotoDetailsBinding: FlickrPhotoDetailsActivityBinding

    companion object {
        private const val ARG_PHOTO = "photo"
        fun getIntent(context: Context, photo: Photo): Intent {
            var intent = Intent(context, FlickrPhotoDetailsActivity::class.java)
            intent.putExtra(ARG_PHOTO, photo)
            return intent
        }

        const val VIEW_NAME_IMAGE = "detail:image"
        const val VIEW_NAME_TITLE = "detail:title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flickrPhotoDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.flickr_photo_details_activity)
        var photo = intent?.getParcelableExtra(ARG_PHOTO) as Photo
        ViewCompat.setTransitionName(flickrPhotoDetailsBinding.fullscreenView, VIEW_NAME_IMAGE)
        flickrPhotoDetailsBinding.model = photo
        flickrPhotoDetailsBinding.executePendingBindings()
    }
}