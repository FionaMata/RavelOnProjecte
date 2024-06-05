package com.mataecheverry.project_ravelry.dades.xarxa.api.auth

import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_ID
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_SECRET
import nl.myndocs.oauth2.token.AccessToken
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

public interface RavelryAuthService {
    @Headers("Accept: application/json")
    @POST("oauth2/auth")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String = CLIENT_ID,
        @Field("client_secret") clientSecret: String = CLIENT_SECRET,
        @Field("code") code: String = "code",
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String = "authorization_code"

        ): Response<AccessToken>


    @Headers("Accept: application/json")
    @POST("oauth2/token")
    @FormUrlEncoded
    fun refreshToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = "refresh_token"
    ): Response<AccessToken>
}


