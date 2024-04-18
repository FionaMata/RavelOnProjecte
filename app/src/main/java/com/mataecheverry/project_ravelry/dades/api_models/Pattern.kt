package com.mataecheverry.project_ravelry.dades.api_models


import com.google.gson.annotations.SerializedName

data class Pattern(
    @SerializedName("designer")
    val designer: Designer,
    @SerializedName("first_photo")
    val firstPhoto: FirstPhoto,
    @SerializedName("free")
    val free: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("pattern_author")
    val patternAuthor: PatternAuthor,
    @SerializedName("pattern_sources")
    val patternSources: List<PatternSource>,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("personal_attributes")
    val personalAttributes: Any
)