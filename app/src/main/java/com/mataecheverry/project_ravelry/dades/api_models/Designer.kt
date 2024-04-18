package com.mataecheverry.project_ravelry.dades.api_models


import com.google.gson.annotations.SerializedName

data class Designer(
    @SerializedName("crochet_pattern_count")
    val crochetPatternCount: Int,
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("knitting_pattern_count")
    val knittingPatternCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("patterns_count")
    val patternsCount: Int,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("users")
    val users: List<User>
)