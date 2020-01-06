package com.john.flickr.search.model

import android.os.Parcel
import android.os.Parcelable


data class Photo(
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: String,
    var title: String,
    var partialUrl: String,
    var ispublic: Int = 0,
    var isfriend: Int = 0,
    var isfamily: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(owner)
        dest?.writeString(secret)
        dest?.writeString(server)
        dest?.writeString(farm)
        dest?.writeString(title)
        dest?.writeString(partialUrl)
        dest?.writeInt(ispublic)
        dest?.writeInt(isfriend)
        dest?.writeInt(isfamily)
    }

//    override fun toString(): String {
//        return partialUrl
//    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}