package com.mataecheverry.project_ravelry.dades.xarxa.api

import android.util.Log
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthReply
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_ID
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_SECRET
import com.mataecheverry.project_ravelry.dades.autenticacio.LOGIN_URL
import com.mataecheverry.project_ravelry.dades.xarxa.api.auth.RavelryAuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import nl.myndocs.oauth2.token.AccessToken
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RavelryClient {

    var clientId = CLIENT_ID
    var clientSecret = CLIENT_SECRET
    var accessToken = ""
    var authorizationToken = ""
    var redirectURI = "https://ravelschool-bc44d.firebaseapp.com/__/auth/handler"



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
                    refreshToken()
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
        .baseUrl(LOGIN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: RavelryServei by lazy {
        apiRetrofit.create(RavelryServei::class.java)
    }

    suspend fun exchangeToken(code: String): AuthReply<AccessToken> {
        val authService = authRetrofit.create(RavelryAuthService::class.java)
        return try {
            val response = authService.getAccessToken(
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET,
                code = code,
                redirectUri = RavelryClient.redirectURI,
                grantType = "authorization_code"
            )
            if (response.isSuccessful) {
                response.body()?.let { accessToken ->
                    RavelryClient.accessToken = accessToken.accessToken
                    AuthReply.Success(accessToken)
                } ?: AuthReply.Failed("Failed to get access token")
            } else {
                AuthReply.Failed("Failed to get access token: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            AuthReply.Failed("Exception: ${e.message}")
        }
    }


    private suspend fun refreshToken() = withContext(Dispatchers.IO) {
        val authService = authRetrofit.create(RavelryAuthService::class.java)
        val authHeader = Credentials.basic(clientId, clientSecret)
        try {
            val response = authService.getAccessToken(

                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET,
                code = accessToken, //hauria de ser el token que rebem per primer cop
                redirectURI,
                grantType = "authorization_code")

            if (response.isSuccessful && response.body()?.refreshToken != null)
                accessToken = response.body()?.refreshToken.toString()
//            if (response.accessToken.isNotEmpty()) {
//                accessToken = response.accessToken

        } catch (ex: Exception) {
            Log.d("REFRESH_TOKEN --> ", "EXCEPTION --> $ex.printStackTrace() ¡")
            ex.printStackTrace()
        }
    }
}
