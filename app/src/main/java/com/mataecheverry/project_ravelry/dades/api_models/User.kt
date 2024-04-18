package com.mataecheverry.project_ravelry.dades.api_models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("photo_url")
    val photoUrl: String,
    @SerializedName("small_photo_url")
    val smallPhotoUrl: String,
    @SerializedName("tiny_photo_url")
    val tinyPhotoUrl: String,
    @SerializedName("username")
    val username: String
)