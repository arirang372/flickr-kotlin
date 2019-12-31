package com.john.flickr.search.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.john.flickr.data.Photo
import com.john.flickr.search.viewmodel.FlickrSearchViewModel
import java.util.*


class PhotoAdapter(photoSize: Int, thumbnail: Boolean, viewModel : FlickrSearchViewModel) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>(),
    ListPreloader.PreloadModelProvider<Photo> {
    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView as ImageView
    }

    private var mPhotoSize = photoSize
    private var mThumbnail = thumbnail
    private lateinit var mContext: Context
    private var photos: List<Photo> = Collections.emptyList()
    private var mViewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(parent)
    }

    override fun getItemId(position: Int): Long {
        return RecyclerView.NO_ID
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
    }

    override fun getPreloadItems(position: Int): List<Photo> {
        return photos.subList(position, position + 1)
    }

    override fun getPreloadRequestBuilder(item: Photo): RequestBuilder<*>? {
        return Glide.with(mContext).asDrawable().centerCrop().load(item)
    }
}