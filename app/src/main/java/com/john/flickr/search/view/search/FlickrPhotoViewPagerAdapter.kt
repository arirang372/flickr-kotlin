package com.john.flickr.search.view.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.john.flickr.search.model.Photo
import java.util.*

class FlickrPhotoViewPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {
    private var photos: MutableList<Photo> = Collections.emptyList()
    override fun getItem(position: Int): Fragment {
        return FlickrPhotoDetailsFragment.newInstance(photos[position])
    }

    override fun getCount(): Int {
        return photos.size
    }

    fun setPhotos(photos: MutableList<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }
}