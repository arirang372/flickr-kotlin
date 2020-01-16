package com.john.flickr.search.view.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.john.flickr.databinding.FlickrPhotoListItemBinding
import com.john.flickr.search.model.Photo
import com.john.flickr.search.view.search.callbacks.PhotoItemListener
import com.john.flickr.search.viewmodel.FlickrSearchViewModel
import java.util.*

class FlickrPhotoListAdapter(viewModel: FlickrSearchViewModel) :
    RecyclerView.Adapter<FlickrPhotoListAdapter.PhotoTileViewHolder>(),
    ListPreloader.PreloadModelProvider<Photo> {
    class PhotoTileViewHolder(binding: FlickrPhotoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val listItemBinding: FlickrPhotoListItemBinding = binding
    }

    private lateinit var mContext: Context
    private var photos: MutableList<Photo>? = Collections.emptyList()
    private val mViewModel: FlickrSearchViewModel = viewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoTileViewHolder {
        mContext = parent.context
        var binding =
            FlickrPhotoListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return PhotoTileViewHolder(
            binding
        ).apply {
            ViewPreloadSizeProvider<Photo>().setView(this.listItemBinding.photoView)
        }
    }

    override fun getItemCount(): Int {
        return photos!!.size
    }

    override fun onBindViewHolder(holder: PhotoTileViewHolder, position: Int) {
        var photo = this!!.photos!![position]
        holder.listItemBinding.model = photo
        holder.listItemBinding.callback = object : PhotoItemListener {
            override fun onItemClicked(photo: Photo) {
                //go to the next Activity...
                mViewModel.setSelectedPhoto(holder.listItemBinding.photoView, photo)
            }
        }
        holder.listItemBinding.executePendingBindings()
    }

    override fun getPreloadItems(position: Int): MutableList<Photo> {
        return photos?.subList(position, position + 1)!!
    }

    override fun getPreloadRequestBuilder(item: Photo): RequestBuilder<*>? {
        return null
    }

    fun setPhotos(photos: MutableList<Photo>?) {
        this.photos = photos
        notifyDataSetChanged()
    }
}