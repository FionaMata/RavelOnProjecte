package com.mataecheverry.project_ravelry.models.app_models

import com.google.gson.annotations.SerializedName
data class AccessToken(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String
)