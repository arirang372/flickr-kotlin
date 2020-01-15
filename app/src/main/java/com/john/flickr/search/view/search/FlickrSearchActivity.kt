package com.john.flickr.search.view.search

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.john.flickr.R
import com.john.flickr.ViewModelFactory
import com.john.flickr.data.Page
import com.john.flickr.data.source.remote.RemoteDataLoader.Companion.DEFAULT_SEARCH_TEXT
import com.john.flickr.databinding.FlickrSearchActivityBinding
import com.john.flickr.search.view.details.FlickrPhotoDetailsActivity
import com.john.flickr.search.viewmodel.FlickrSearchViewModel
import kotlinx.android.synthetic.main.flickr_search_activity.*


class FlickrSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var searchActivityBinding: FlickrSearchActivityBinding

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
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(this)
        searchActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.flickr_search_activity)
        view_pager.pageMargin = resources.getDimensionPixelOffset(R.dimen.page_margin)
        view_pager.adapter = FlickrPagerAdapter(this)
        viewModel.executeSearch(DEFAULT_SEARCH_TEXT)
        viewModel.getOpenTaskEvent().observe(this, Observer {
            openDetailPage(it)
        })
    }

    private fun openDetailPage(pair: Pair<Int, String>) {
        var activityOption = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            searchActivityBinding.root.findViewById(pair.first),
            pair.second
        )
        startActivity(FlickrPhotoDetailsActivity.getIntent(this, photo))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.executeSearch(it) }
        searchView.setQuery("", false)
        hideKeyboard()
        return true
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
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
