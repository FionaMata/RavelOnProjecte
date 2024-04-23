package com.mataecheverry.project_ravelry.dades.xarxa.api

import com.mataecheverry.project_ravelry.models.api_models.APIReplyGetPatterns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RavelryHelperImpl(private val apiService: RavelryServei) : RavelryHelper {

    override suspend fun getHotRightNow(
        sort: String,
        page: Int,
        pageSize: Int
    ): Flow<APIReplyGetPatterns> = flow {
        emit(apiService.getHotRightNow(sort, page, pageSize))
    }


    override suspend fun getDebutPatterns(
        craft: String,
        photo: String,
        debutPattern: String,
        sort: String,
        view: String
    ): Flow<APIReplyGetPatterns> = flow {
        emit(apiService.getDebutPatterns(craft,photo,debutPattern,sort, view))
    }
}