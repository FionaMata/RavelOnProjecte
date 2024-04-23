package com.mataecheverry.project_ravelry.models.api_models


import com.google.gson.annotations.SerializedName
import com.mataecheverry.project_ravelry.models.app_models.AppPatternAuthor

data class PatternAuthor(
    @SerializedName("crochet_pattern_count")
    val crochetPatternCount: Int,
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("id")
    var id: Int,
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
fun PatternAuthor.toAppPatternAuthor(): AppPatternAuthor = AppPatternAuthor(
    id = id,
    name = name,
    users = users
)

