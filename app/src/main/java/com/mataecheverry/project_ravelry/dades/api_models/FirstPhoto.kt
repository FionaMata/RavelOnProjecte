package com.mataecheverry.project_ravelry.dades.api_models


import com.google.gson.annotations.SerializedName

data class FirstPhoto(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("caption_html")
    val captionHtml: String,
    @SerializedName("copyright_holder")
    val copyrightHolder: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("medium2_url")
    val medium2Url: String,
    @SerializedName("medium_url")
    val mediumUrl: String,
    @SerializedName("small2_url")
    val small2Url: String,
    @SerializedName("small_url")
    val smallUrl: String,
    @SerializedName("sort_order")
    val sortOrder: Int,
    @SerializedName("square_url")
    val squareUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("x_offset")
    val xOffset: Int,
    @SerializedName("y_offset")
    val yOffset: Int
)