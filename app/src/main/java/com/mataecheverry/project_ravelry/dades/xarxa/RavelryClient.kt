package com.mataecheverry.project_ravelry.dades.xarxa

import android.accounts.AccountManager
import android.os.Bundle
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_ID
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_SECRET
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_API
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_AUTH
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_TOKEN
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RavelryClient {
    const val BASE_URL = URL_API
    const val AUTH_BASE_URL = URL_AUTH
    const val TOKEN_URL = URL_TOKEN
    private var clientId = CLIENT_ID
    private var clientSecret = CLIENT_SECRET
    private var accessToken = ""

    private val authRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
//
//    private val retrofit : Retrofit by lazy {
//        Retrofit.Builder()
//
//    }



}


