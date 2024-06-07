package com.mataecheverry.project_ravelry.dades.xarxa.api

//
//object RavelryClient {
//
//    private var clientId = CLIENT_ID
//    private var clientSecret = CLIENT_SECRET
//    var accessToken = ""
//    private var redirectURI = "https://ravelschool-bc44d.firebaseapp.com/__/auth/handler"
//
//
//
//    private val httpClient =  OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            var request = chain.request()
//            val response = chain.proceed(request)
//            val errorCode = response.code
//            val errorMessage = response.message
//
//            if (!response.isSuccessful) {
//                Log.e("CLIENT", "HTTP error: $errorCode - $errorMessage")
//            } else {
//                Log.e("CLIENT", "HTTP: ${response.code} - ${response.message}")
//            }
//
//            if (accessToken.isEmpty() || errorCode == 401) {
//                Log.d("TOKEN", ":$accessToken")
//                runBlocking {
//                    exchangeToken(RavelryClient.accessToken)
//                    refreshToken()
//                }
//                // Retry the request with the new token
//                request = chain.request().newBuilder()
//                    .header("Authorization", "Bearer $accessToken")
//                    .build()
//                return@addInterceptor chain.proceed(request)
//            }
//            response
//        }.build()
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.ravelry.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(httpClient)
//        .build()
//
//
//    private val authService: RavelryAuthService by lazy {
//        retrofit.create(RavelryAuthService::class.java)
//    }
//
//    val service: RavelryServei by lazy {
//        retrofit.create(RavelryServei::class.java)
//    }
//
//    suspend fun exchangeToken(code: String): AuthReply<AccessToken> {
//        return try {
//            val response = authService.getAccessToken(
//                clientId = clientId,
//                clientSecret = clientSecret,
//                code = code,
//                redirectUri = redirectURI,
//                grantType = "authorization_code"
//            )
//            Log.d("CLIENT", "ExchangeToken: $response")
//            if (response.isSuccessful) {
//                response.body()?.let { accessToken ->
//                    RavelryClient.accessToken = accessToken.accessToken
//                    AuthReply.Success(accessToken)
//                } ?: AuthReply.Failed("Failed to get access token")
//            } else {
//                AuthReply.Failed("Failed to get access token: ${response.errorBody()?.string()}")
//            }
//        } catch (e: Exception) {
//            AuthReply.Failed("Exception: ${e.message}")
//        }
//    }
//
//
//
//    private suspend fun refreshToken() = withContext(Dispatchers.IO) {
//        try {
//            val response = authService.getAccessToken(
//                clientId = CLIENT_ID,
//                clientSecret = CLIENT_SECRET,
//                code = accessToken,
//                redirectUri = redirectURI,
//                grantType = "refresh_token"
//            )
//            if (response.isSuccessful && response.body()?.refreshToken != null) {
//                accessToken = response.body()?.refreshToken.toString()
//                Log.d("CLIENT", "RefreshToken: $accessToken")
//
//            }
//        } catch (ex: Exception) {
//            Log.d("CLIENT --> ", " RefreshToken EXCEPTION --> $ex")
//            ex.printStackTrace()
//        }
//    }
//}
