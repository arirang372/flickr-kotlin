package com.john.flickr.data.source.remote

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.john.flickr.data.FlickrResponse
import com.john.flickr.data.Photo
import com.john.flickr.data.source.Photos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.function.Function

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
    }

    @SuppressLint("CheckResult")
    fun loadAllPhotos(text : String) : MutableLiveData<MutableList<Photo>>
    {
        var photos = MutableLiveData<MutableList<Photo>>()
        service.getPhotoContents(METHOD, MAX_PER_PAGE, RESPONSE_FORMAT, API_KEY, DEFAULT_SEARCH_TEXT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map{ it.photos }

        return photos
    }
}