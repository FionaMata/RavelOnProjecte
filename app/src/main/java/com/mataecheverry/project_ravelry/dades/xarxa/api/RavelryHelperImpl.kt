package com.mataecheverry.project_ravelry.dades.xarxa.api

import com.mataecheverry.project_ravelry.models.api_models.APIReplyGetPatterns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RavelryHelperImpl(private val apiService: RavelryServei) : RavelryHelper {
    override suspend fun getCurrentUser() {
        return apiService.getCurrentUser()
    }

    override suspend fun getHotRightNow(
        sort: String,
        page: Int,
        pageSize: Int,
        view: String,
        photo: Boolean
    ): Flow<APIReplyGetPatterns> = flow {
        val v:APIReplyGetPatterns = apiService.getHotRightNow(sort, page, pageSize)
        emit(v)
    }

    override suspend fun getDebutPatterns(
        craft: String,
        photo: String,
        debutPattern: Boolean,
        sort: String,
        view: String
    ): Flow<APIReplyGetPatterns> = flow {
        emit(apiService.getDebutPatterns(craft,photo,debutPattern,sort, view))
    }

    override suspend fun getPatterns(
        craft: String,
        page: Int,
        pageSize: Int,
        sort: String,
        view: String,
        photo: Boolean
    ): Flow<APIReplyGetPatterns> = flow {
        emit(apiService.getPatterns(craft, photo, sort, view, page, pageSize))
    }

}