package com.adarsh.flickrapp.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PhotosResponse(@SerializedName("photos") val photos: PhotosMetaDataResponse)

data class PhotosMetaDataResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perpage: Int,
    @SerializedName("total") val total: String,
    @SerializedName("photo") val photoList: List<PhotoMetaDataDetails>,
    @SerializedName("stat") val status: String
)

@Entity
data class PhotoMetaDataDetails(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("owner") val owner: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: String,
    @SerializedName("farm") val farm: Int,
    @SerializedName("title") val title: String,
    @SerializedName("ispublic") val isPublic: Int,
    @SerializedName("isfriend") val isFriend: Int,
    @SerializedName("isfamily") val isFamily: Int
) : Serializable {
    fun getThumbnailUrl(): String = "https://live.staticflickr.com/$server/${id}_${secret}_t.jpg"

    fun getUrl(): String = "https://live.staticflickr.com/$server/${id}_${secret}_b.jpg"
}