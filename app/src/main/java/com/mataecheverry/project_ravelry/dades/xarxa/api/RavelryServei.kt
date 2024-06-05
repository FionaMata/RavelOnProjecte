package com.mataecheverry.project_ravelry.dades.xarxa.api

import com.mataecheverry.project_ravelry.dades.autenticacio.LOGIN_URL
import com.mataecheverry.project_ravelry.models.api_models.APIReplyGetPatterns
import com.mataecheverry.project_ravelry.models.app_models.LoginRequest
import com.mataecheverry.project_ravelry.models.app_models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RavelryServei {



    @GET("current_user.json")
    suspend fun getCurrentUser()

    @GET("patterns/search.json")
    suspend fun getHotRightNow(
        @Query("sort") sort: String ="recently-popular",
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 40
    ): APIReplyGetPatterns

    @GET("patterns/search.json")
    suspend fun getDebutPatterns(
        @Query("craft") craft: String ="knitting|crochet",
        @Query("photo") photo: String ="yes",
        @Query("debut-pattern") debutPattern: Boolean = true,
        @Query("sort") sort: String = "created",
        @Query("view") view: String = "captioned_thumbs"
    ): APIReplyGetPatterns

    @GET("patterns/search.json")
    suspend fun getPatterns(
        @Query("craft") craft: String ="knitting|crochet",
        @Query("photo") photo: Boolean = true,
        @Query("sort") sort: String = "created",
        @Query("view") view: String = "captioned_thumbs",
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 40
    ): APIReplyGetPatterns

}