package com.john.flickr.search.view.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.john.flickr.databinding.FlickrPhotoGridItemBinding
import com.john.flickr.search.model.Photo
import com.john.flickr.search.view.details.FlickrPhotoDetailsActivity
import com.john.flickr.search.view.search.callbacks.PhotoItemListener
import java.util.*


class FlickrPhotoGridAdapter(photoSize: Int, thumbnail: Boolean) :
    RecyclerView.Adapter<FlickrPhotoGridAdapter.PhotoViewHolder>(),
    ListPreloader.PreloadModelProvider<Photo> {
    class PhotoViewHolder(binding: FlickrPhotoGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val gridItemBinding: FlickrPhotoGridItemBinding = binding
    }

    private var mPhotoSize = photoSize
    private var mThumbnail = thumbnail
    private lateinit var mContext: Context
    private var photos: MutableList<Photo> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        mContext = parent.context
        var binding: FlickrPhotoGridItemBinding =
            FlickrPhotoGridItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        binding.root?.layoutParams?.apply {
            width = mPhotoSize
            height = mPhotoSize
        }
        return PhotoViewHolder(
            binding
        )
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
        holder.gridItemBinding.model = this!!.photos[position]
        holder.gridItemBinding.thumbnailValue = mThumbnail
        holder.gridItemBinding.callback = object : PhotoItemListener {
            override fun onItemClicked(photo: Photo) {
                //go to the next Activity...
                mContext.startActivity(FlickrPhotoDetailsActivity.getIntent(mContext, photo))
            }
        }
        holder.gridItemBinding.executePendingBindings()
    }

    override fun getPreloadItems(position: Int): List<Photo> {
        return photos.subList(position, position + 1)
    }

    override fun getPreloadRequestBuilder(item: Photo): RequestBuilder<*>? {
        return Glide.with(mContext).asDrawable().centerCrop().load(item)
    }

    fun setPhotos(photos: MutableList<Photo>?) {
        this.photos = photos!!
        notifyDataSetChanged()
    }
}