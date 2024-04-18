package com.mataecheverry.project_ravelry.dades.xarxa.api.auth

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface RavelryAuthService {
    @FormUrlEncoded
    @POST("oauth2/auth")
    suspend fun authenticate(
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("code") code: String,
        @Header("authorization") authorization: String
    ): RavelryAuthResponse
}