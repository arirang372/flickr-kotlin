package com.john.flickr.data.source.remote

import android.annotation.SuppressLint
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.john.flickr.data.AppConstant.Companion.getPhotoURL
import com.john.flickr.data.FlickrResponse
import com.john.flickr.data.source.Photos
import com.john.flickr.data.source.PhotosDataSource
import com.john.flickr.search.model.Photo
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataLoader {
    private var service: FlickrService

    init {
        var retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        service = retrofit.create(FlickrService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.flickr.com"
        const val API_KEY = "f0e6fbb5fdf1f3842294a1d21f84e8a6"
        const val METHOD = "flickr.photos.getRecent"
        const val MAX_PER_PAGE = 300
        const val RESPONSE_FORMAT = "json"
        const val DEFAULT_SEARCH_TEXT = "kitten"
        const val CACHEABLE_PHOTO_URL = "http://farm%s.staticflickr.com/%s/%s_%s_"
    }

    @SuppressLint("CheckResult")
    fun loadAllPhotos(
        text: String,
        callback: PhotosDataSource.LoadPhotosCallback
    ): ObservableList<Photo> {
        var photoLiveData = ObservableArrayList<Photo>()
        service.getPhotoContents(
            METHOD,
            MAX_PER_PAGE,
            RESPONSE_FORMAT,
            API_KEY,
            text,
            1
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response: FlickrResponse -> response.photos }
            .map { photos: Photos -> photos.photo }
            .map { createPhotoUrl(it) }
            .subscribe(object : Observer<MutableList<Photo>> {
                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                    callback.onPhotoNotAvailable()
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(photos: MutableList<Photo>) {
                    photoLiveData.clear()
                    photoLiveData.addAll(photos)
                    // callback.onPhotosLoaded(photos)
                }
            })
        return photoLiveData
    }

    private fun createPhotoUrl(photos: MutableList<Photo>): MutableList<Photo> {
        for (p in photos) {
            p.partialUrl = String.format(CACHEABLE_PHOTO_URL, p.farm, p.server, p.id, p.secret)
            p.partialUrl = getPhotoURL(p, 55, 55)
        }
        return photos
    }
}