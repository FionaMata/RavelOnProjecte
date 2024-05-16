package com.mataecheverry.project_ravelry.dades.xarxa.api

import com.mataecheverry.project_ravelry.models.api_models.APIReplyGetPatterns
import kotlinx.coroutines.flow.Flow

interface RavelryHelper {
    suspend fun getCurrentUser()
    suspend fun getHotRightNow(
        sort: String ="recently-popular",
        page: Int = 1,
        pageSize: Int = 40,
        view: String = "captioned_thumbs",
        photo: Boolean = true
    ): Flow<APIReplyGetPatterns>

    suspend fun getDebutPatterns(
        craft: String ="knitting|crochet",
        photo: String = "yes",
        debutPattern: Boolean = true,
        sort: String = "created",
        view: String = "captioned_thumbs"
    ): Flow<APIReplyGetPatterns>

    suspend fun getPatterns(
        craft: String ="knitting|crochet",
        page: Int = 1,
        pageSize: Int = 40,
        sort: String = "crafted",
        view: String = "captioned_thumbs",
        photo: Boolean = true
    ): Flow<APIReplyGetPatterns>

}