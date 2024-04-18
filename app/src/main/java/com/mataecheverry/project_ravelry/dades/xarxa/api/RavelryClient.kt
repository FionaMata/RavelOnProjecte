package com.mataecheverry.project_ravelry.dades.xarxa.api

import com.google.gson.GsonBuilder
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_ID
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_SECRET
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_API
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_AUTH
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_TOKEN
import com.mataecheverry.project_ravelry.dades.xarxa.api.auth.RavelryAuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RavelryClient {

    const val BASE_URL = URL_API
    const val AUTH_BASE_URL = URL_AUTH
    const val TOKEN_URL = URL_TOKEN
    private var clientId = CLIENT_ID
    private var clientSecret = CLIENT_SECRET
    private var accessToken = ""
    private var redirectURI = "ravelon://oauth-callback/ravelry"


    private val httpClient =  OkHttpClient.Builder().addInterceptor { chain ->
        if (accessToken.isEmpty()) {
            runBlocking {
                refreshToken()
            }
        }
        //Log.d("TOKEN --> ", "Token --> $accessToken")
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
        chain.proceed(request)
    }.build()

    private val apiRetrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(httpClient)
            .build()
    }

    val service: RavelryServei by lazy {
        apiRetrofit.create(RavelryServei::class.java)
    }


    private val authRetrofit = Retrofit.Builder()
        .baseUrl(AUTH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val tokenRetrofit = Retrofit.Builder()
        .baseUrl(TOKEN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()




    private suspend fun refreshToken() = withContext(Dispatchers.IO) {
        val authService = authRetrofit.create(RavelryAuthService::class.java)
        val authHeader = Credentials.basic(clientId, clientSecret)
        try {
            val response = authService.authenticate(authorization = authHeader, code="code")
            if (response.accessToken.isNotEmpty()){
                accessToken = response.accessToken
            }
        }
        catch (ex: Exception){
            //Log.d("REFRESH_TOKEN --> ", "EXCEPTION --> $ex.printStackTrace()")
            ex.printStackTrace()
        }
    }
}


