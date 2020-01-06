package com.john.flickr.search.view.search

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.bumptech.glide.Glide
import com.john.flickr.R
import com.john.flickr.data.Page
import com.john.flickr.search.view.search.FlickrSearchActivity.Companion.PAGE_TO_TITLE

class FlickrPagerAdapter(activity: FragmentActivity) :
    FragmentPagerAdapter(activity.supportFragmentManager) {
    val context = activity.applicationContext
    var mLastPosition = -1
    lateinit var mLastFragment: Fragment

    override fun getItem(position: Int): Fragment {
        return pageToFragment(position)
    }

    override fun getCount(): Int {
        return Page.values().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var page: Page = Page.values()[position]
        var titleId: Int? = PAGE_TO_TITLE[page]
        return titleId?.let { context.getString(it) }
    }

    fun getPageSize(id: Int): Int {
        return context.resources.getDimensionPixelSize(id)
    }

    private fun pageToFragment(position: Int): Fragment {
        var page = Page.values()[position]
        return when (page) {
            Page.SMALL -> FlickrPhotoGridFragment.newInstance(
                getPageSize(R.dimen.small_photo_side),
                15,
                false
            )
            Page.MEDIUM -> FlickrPhotoGridFragment.newInstance(
                getPageSize(R.dimen.medium_photo_side),
                10,
                true
            )
            Page.LIST -> FlickrPhotoListFragment.newInstance()
        }
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (position != mLastPosition) {
            if (mLastPosition >= 0)
                Glide.with(mLastFragment).pauseRequests()
            var current = `object` as Fragment
            mLastPosition = position
            mLastFragment = current
            if (current.isAdded)
                Glide.with(current).resumeRequests()
        }
    }

}