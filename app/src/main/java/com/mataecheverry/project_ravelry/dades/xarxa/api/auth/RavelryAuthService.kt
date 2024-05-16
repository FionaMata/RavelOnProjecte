package com.mataecheverry.project_ravelry.dades.xarxa.api.auth

import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RavelryAuthService {
    @Headers("token")
    @POST("oauth2/auth")
    suspend fun authenticate(
        @Field("grant_type") grantType: String = "authorization_code",
        @Field("token_type") tokenType: String = "bearer",
        @Field("redirect_uri") redirectUri: String ="",
        @Field("access_token_url") accessTokenUrl: String = "",
        @Field("client_id") clientId: String = "",
        @Field("client_secret")  clientSecret: String ="",
        @Field("scope")  scope: String ="",
        @Field("state")  state: String = "",
        @Header("authorization") authorization: String
    ): RavelryAuthResponse
}