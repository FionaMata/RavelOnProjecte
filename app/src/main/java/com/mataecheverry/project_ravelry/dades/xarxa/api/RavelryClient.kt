package com.mataecheverry.project_ravelry.dades.xarxa.api

import android.util.Log
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_ID
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_SECRET
import com.mataecheverry.project_ravelry.dades.autenticacio.URL_AUTH
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RavelryClient {

    var clientId = CLIENT_ID
    var clientSecret = CLIENT_SECRET
    var accessToken = ""
    var authorizationToken = ""
    var redirectURI = "project_ravelry://callback"


//    private val oauthClient = OkHttpClient.Builder()
//        .addInterceptor(authorizationInterceptor)
//        .build()

    private val httpClient =  OkHttpClient.Builder()
        .addInterceptor { chain ->
            var request = chain.request()
            val response = chain.proceed(request)
            val errorCode = response.code
            val errorMessage = response.message

            if (!response.isSuccessful){

                Log.e("ERROR RETROFIT", "HTTP error: $errorCode - $errorMessage")
            }
            else if (response.isSuccessful){
                val code = response.code
                val message = response.message
                Log.e("RESPOSTA RETROFIT", "HTTP: $code - $message")
            }
            if (accessToken.isEmpty() || errorCode == 401) {
                Log.d("TOKEN --> ", "Token --> $accessToken")
                runBlocking {
                    //refreshToken()
                }
            }
            request = chain.request().newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
            response.close()
            chain.proceed(request)
        }.build()


    private val apiRetrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.ravelry.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    private val authRetrofit = Retrofit.Builder()
        .baseUrl(URL_AUTH)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RavelryServei by lazy {
        apiRetrofit.create(RavelryServei::class.java)
    }

//    private suspend fun refreshToken() = withContext(Dispatchers.IO) {
//        val authService = authRetrofit.create(RavelryAuthService::class.java)
//        val authHeader = Credentials.basic(clientId, clientSecret)
//        try {
//            val response = authService.getAccessToken(
//
//                clientId = CLIENT_ID,
//                clientSecret = CLIENT_SECRET,
//                code = code,
//
//                )
//            if (response.accessToken.isNotEmpty()) {
//                accessToken = response.accessToken
//            }
//        } catch (ex: Exception) {
//            Log.d("REFRESH_TOKEN --> ", "EXCEPTION --> $ex.printStackTrace() ¡")
//            ex.printStackTrace()
//        }
//    }
}
