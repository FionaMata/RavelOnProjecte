package com.mataecheverry.project_ravelry.models.api_models


import com.google.gson.annotations.SerializedName

data class Paginator(
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("page_count")
    val pageCount: Int,
    @SerializedName("page_size")
    val pageSize: Int,
    @SerializedName("results")
    val results: Int
)