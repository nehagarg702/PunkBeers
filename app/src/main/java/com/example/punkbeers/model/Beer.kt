package com.example.punkbeers.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("image_url") val imageUrl: String,
    val abv: Double,
    @SerializedName("food_pairing")val foodPairing: List<String>,
    @SerializedName("brewers_tips")val brewersTips: String,
    @SerializedName("contributed_by")val contributedBy: String
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Beer> {
            override fun createFromParcel(parcel: Parcel) = Beer(parcel)
            override fun newArray(size: Int): Array<Beer?> = arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(tagline)
        dest.writeString(description)
        dest.writeString(imageUrl)
        dest.writeDouble(abv)
        dest.writeList(foodPairing)
        dest.writeString(brewersTips)
        dest.writeString(contributedBy)
    }

    override fun describeContents() = 0

}