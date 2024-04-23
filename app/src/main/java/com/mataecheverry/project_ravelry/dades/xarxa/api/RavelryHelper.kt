package com.mataecheverry.project_ravelry.dades.xarxa.api

import com.mataecheverry.project_ravelry.models.api_models.APIReplyGetPatterns
import kotlinx.coroutines.flow.Flow

interface RavelryHelper {

    suspend fun getHotRightNow(
        sort: String ="recently-popular",
        page: Int = 1,
        pageSize: Int = 10
    ): Flow<APIReplyGetPatterns>

    suspend fun getDebutPatterns(
        craft: String ="knitting|crochet",
        photo: String ="yes",
        debutPattern: String ="yes",
        sort: String = "created",
        view: String = "captioned_thumbs"
    ): Flow<APIReplyGetPatterns>

}