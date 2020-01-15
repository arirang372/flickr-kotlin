package com.john.flickr.search.view.search

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.john.flickr.data.AppConstant.Companion.SQUARE_THUMB_SIZE

object FlickrSearchBindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["android:src", "app:thumbnail"], requireAll = true)
    fun setImageUrl(
        imageView: AppCompatImageView,
        thumbnail: Boolean,
        url: String
    ) {
        Glide.with(imageView.context).asDrawable().centerCrop()
            .load(url)
            .thumbnail(if (thumbnail) Glide.with(imageView.context).load(url) else null)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, src: String) {
        Glide.with(imageView.context).asDrawable().centerCrop()
            .placeholder(ColorDrawable(Color.GRAY))
            .load(src)
            .thumbnail(
                Glide.with(imageView.context).asDrawable()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .override(SQUARE_THUMB_SIZE)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .load(src)
            )
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("app:gridItems")
    fun setGridItems(
        recyclerView: RecyclerView,
        items: MutableList<com.john.flickr.search.model.Photo>
    ) {
        var adapter: PhotoGridAdapter = recyclerView.adapter as PhotoGridAdapter
        adapter.setPhotos(items)
    }

    @JvmStatic
    @BindingAdapter("app:listItems")
    fun setListItems(
        listView: RecyclerView,
        items: MutableList<com.john.flickr.search.model.Photo>
    ) {
        var adapter: FlickrPhotoListAdapter = listView.adapter as FlickrPhotoListAdapter
        adapter.setPhotos(items)
    }
}

