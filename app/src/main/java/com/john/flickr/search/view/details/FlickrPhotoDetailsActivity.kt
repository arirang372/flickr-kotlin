package com.john.flickr.search.view.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.john.flickr.R
import com.john.flickr.ViewModelFactory
import com.john.flickr.databinding.FlickrPhotoDetailsActivityBinding
import com.john.flickr.search.model.Photo
import com.john.flickr.search.view.search.FlickrPhotoViewPagerAdapter
import com.john.flickr.search.viewmodel.FlickrDetailsViewModel


class FlickrPhotoDetailsActivity : AppCompatActivity() {
    private lateinit var flickrPhotoDetailsActivityBinding: FlickrPhotoDetailsActivityBinding

    companion object {
        private const val ARG_PHOTO = "photo"
        fun getIntent(context: Context, photo: Photo): Intent {
            var intent = Intent(context, FlickrPhotoDetailsActivity::class.java)
            intent.putExtra(ARG_PHOTO, photo)
            return intent
        }

        fun obtainViewModel(activity: FragmentActivity): FlickrDetailsViewModel {
            var factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(FlickrDetailsViewModel::class.java)
        }

        const val VIEW_NAME_IMAGE = "detail:image"
        const val VIEW_NAME_TITLE = "detail:title"
    }

    lateinit var viewModel: FlickrDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this)
        flickrPhotoDetailsActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.flickr_photo_details_activity)
        var photo = intent?.getParcelableExtra(ARG_PHOTO) as Photo
        var flickrViewPager: ViewPager = flickrPhotoDetailsActivityBinding.flickrViewPager
        flickrViewPager.adapter = FlickrPhotoViewPagerAdapter(supportFragmentManager)
        flickrViewPager.currentItem = viewModel.currentImageItemPosition
        flickrViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                viewModel.setCurrentImageItemPosition(position)
            }
        })
        ViewCompat.setTransitionName(
            flickrPhotoDetailsActivityBinding.flickrViewPager,
            VIEW_NAME_IMAGE
        )
        flickrPhotoDetailsActivityBinding.viewModel = viewModel
        flickrPhotoDetailsActivityBinding.executePendingBindings()
    }
}