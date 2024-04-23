package com.mataecheverry.project_ravelry.models.api_models


import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import com.mataecheverry.project_ravelry.models.app_models.AppPattern

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

@RequiresApi(Build.VERSION_CODES.O)
fun Pattern.toAppPattern(): AppPattern = AppPattern(
    id = id,
    name = name,
    free = free,
    pattern_author = patternAuthor.toAppPatternAuthor(),
    pattern_sources = patternSources,
    permalink = permalink,
    photos = firstPhoto,
    users = designer.users
)