package com.john.flickr.search.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.john.flickr.data.Page
import com.john.flickr.search.view.FlickrSearchActivity.Companion.PAGE_TO_TITLE

class FlickrPagerAdapter(activity: FragmentActivity) :
    FragmentPagerAdapter(activity.supportFragmentManager) {
    val context = activity.applicationContext
    var mLastPosition = -1
    lateinit var mLastFragment: Fragment

    override fun getItem(position: Int): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

//    private fun pageToFragment(position : Int)
//    {
//        var page = Page.values()[position]
//        return if(page == Page.SMALL)
//    }

}