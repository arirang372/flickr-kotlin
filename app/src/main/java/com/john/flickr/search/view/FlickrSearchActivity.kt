package com.john.flickr.search.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.john.flickr.R
import com.john.flickr.ViewModelFactory
import com.john.flickr.data.Page
import com.john.flickr.data.source.remote.RemoteDataLoader.Companion.DEFAULT_SEARCH_TEXT
import com.john.flickr.databinding.FlickrSearchActivityBinding
import com.john.flickr.search.viewmodel.FlickrSearchViewModel
import kotlinx.android.synthetic.main.flickr_search_activity.*


class FlickrSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var searchActivityBinding: FlickrSearchActivityBinding

    companion object {
        val PAGE_TO_TITLE: Map<Page, Int> = mapOf(
            Page.SMALL to R.string.small,
            Page.MEDIUM to R.string.medium, Page.LIST to R.string.list
        )
    }

    lateinit var viewModel: FlickrSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this)
        searchActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.flickr_search_activity)
        view_pager.pageMargin = resources.getDimensionPixelOffset(R.dimen.page_margin)
        view_pager.adapter = FlickrPagerAdapter(this)
        viewModel.executeSearch(DEFAULT_SEARCH_TEXT)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    fun obtainViewModel(activity: FragmentActivity): FlickrSearchViewModel {
        var factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(FlickrSearchViewModel::class.java)
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment === PhotoViewer::class.java) {
            var photoViewer = fragment as PhotoViewer
            photoViewer.onPhotosUpdated(viewModel.getPhotos().value)
        }
    }
}
