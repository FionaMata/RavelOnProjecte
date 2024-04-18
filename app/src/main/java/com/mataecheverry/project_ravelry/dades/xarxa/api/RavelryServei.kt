package com.mataecheverry.project_ravelry.dades.xarxa.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RavelryServei {

    @GET("patterns/search.json")
    suspend fun getHotRightNow(
        @Query("sort") sort: String ="recently-popular",
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 10
    )//: APIReplyGetHotRightNow

    @GET("patterns/search.json")
    suspend fun getDebutPatterns(
        @Query("craft") craft: String ="knitting|crochet",
        @Query("photo") photo: String ="yes",
        @Query("debut-pattern") debutPattern: String ="yes",
        @Query("sort") sort: String = "created",
        @Query("view") view: String = "captioned_thumbs"
    )
}