package com.john.flickr.search.view.search

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.john.flickr.R
import com.john.flickr.ViewModelFactory
import com.john.flickr.data.Page
import com.john.flickr.data.source.remote.RemoteDataLoader.Companion.DEFAULT_SEARCH_TEXT
import com.john.flickr.databinding.FlickrSearchActivityBinding
import com.john.flickr.search.view.search.callbacks.PhotoViewer
import com.john.flickr.search.viewmodel.FlickrSearchViewModel
import kotlinx.android.synthetic.main.flickr_search_activity.*


class FlickrSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var searchActivityBinding: FlickrSearchActivityBinding

    companion object {
        val PAGE_TO_TITLE: Map<Page, Int> = mapOf(
            Page.SMALL to R.string.small,
            Page.MEDIUM to R.string.medium, Page.LIST to R.string.list
        )

        fun obtainViewModel(activity: FragmentActivity): FlickrSearchViewModel {
            var factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(FlickrSearchViewModel::class.java)
        }
    }

    lateinit var viewModel: FlickrSearchViewModel
    lateinit var searchView: SearchView
    var photoViewers: MutableList<PhotoViewer> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            obtainViewModel(
                this
            )
        searchActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.flickr_search_activity)
        view_pager.pageMargin = resources.getDimensionPixelOffset(R.dimen.page_margin)
        view_pager.adapter =
            FlickrPagerAdapter(this)
        viewModel.executeSearch(DEFAULT_SEARCH_TEXT)

//        if(savedInstanceState == null)
//        {
//            Glide.get(this)
//                .preFillBitmapPool(PreFillType.Builder())
//        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.executeSearch(query)
            searchView.setQuery("", false)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_activity, menu)
        searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.isIconified = false
        searchView.setOnQueryTextListener(this)
        return true
    }
}
