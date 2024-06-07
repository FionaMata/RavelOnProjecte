package com.mataecheverry.project_ravelry.dades.autenticacio

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


sealed class AuthReply<out T>{
    data class Success<T>(val dades: T): AuthReply<T>()
    data class Failed (val errorMessage: String): AuthReply<Nothing>()
}


class AuthManager (private val context: Context) {
    private val firebaseAuth: FirebaseAuth by lazy { com.google.firebase.Firebase.auth }
    private val authManager = Identity.getSignInClient(context)


    suspend fun getUser(): FirebaseUser? = withContext(Dispatchers.IO) {
        FirebaseAuth.getInstance().currentUser
    }

    fun tancaSessio() {
        firebaseAuth.signOut()
        //googleSignInClient.signOut()

    }

    //region MAIL I PASSWORD
    //No podem fer un login amb correu i mot de pas si no està registrat prèviament:
    suspend fun createUserWithMailAndPassword(
        email: String,
        password: String
    ): AuthReply<FirebaseUser?> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            AuthReply.Success(
                result.user ?: throw Exception("EXCEPTION DE CREAR USUARI AMB CORREU I PASSWORD")
            )
        } catch (e: Exception) {
            AuthReply.Failed(e.message ?: "CATCH DE CREACIO D'USUARI NOU.")
        }
    }

    suspend fun passwordForgotten(
        email: String
    ): AuthReply<Unit?> {
        return try {
            val result = firebaseAuth.sendPasswordResetEmail(
                email
            ).await()
            AuthReply.Success(Unit)
        } catch (e: Exception) {
            AuthReply.Failed(e.message ?: "Error resetting password.")
        }
    }

    //Inici de sessió amb correu i contrasenya:
    suspend fun signInwithEmailAndPassword(
        correu: String,
        motDePas: String
    ): AuthReply<FirebaseUser?> {
        return try {
            val resultat = firebaseAuth.signInWithEmailAndPassword(correu, motDePas).await()
            resultat.user.let {
                if (it != null) {
                    //RavelryClient.accessToken = it.getIdToken(true).await().token!!
                }
            }
            AuthReply.Success(
                resultat.user
                    ?: throw Exception("Something went wrong. Check username and password - LIADA 1 EN INICI DE SESSIO")
            )
        } catch (e: Exception) {
            AuthReply.Failed(
                e.message
                    ?: "Something failed while Signing in - FALLO INICI SESSIO. ESTEM AL CATCH."
            )
        }
    }
    //endregion



//    suspend fun iniciDeSessioAmbRavelry(launcherIniciDeSessioAmbRavelry: ActivityResultLauncher<Intent>) {
//        val intent = Intent(
//            //const val LOGIN_URL = "https://www.ravelry.com/oauth2/auth."
//            /** CAL CONTROL DE LA REDIRECT URI :) */
//            Intent.ACTION_VIEW, Uri.parse(
//                "$LOGIN_URL?response_type=code&client_id=$CLIENT_ID&redirect_uri=${"https://ravelschool-bc44d.firebaseapp.com/__/auth/handler"}&state=sheepbaa&scope=offline"
//            )
//        )
//        launcherIniciDeSessioAmbRavelry.launch(intent)
//        handleIntent(intent)
//    }
//
//    suspend fun handleIntent(intent: Intent): AuthReply<String> {
//        return withContext(Dispatchers.IO) {
//            intent.data?.let { uri ->
//                val code = uri.getQueryParameter("code")
//                if (code != null) {
//                    val builder = Retrofit.Builder()
//                        .baseUrl("https://www.ravelry.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
//
//                    val retrofit = builder.build()
//                    val ravClient: RavelryAuthService = retrofit.create(RavelryAuthService::class.java)
//
//                    try {
//                        val response = ravClient.getAccessToken(
//                            clientId = CLIENT_ID,
//                            clientSecret = CLIENT_SECRET,
//                            code = code,
//                            redirectUri = CALLBACK
//                        )
//                        if (response.isSuccessful) {
//                            val accessToken = response.body()?.accessToken ?: "No token received"
//                            Log.d("AUTHMANAGER", "handle intent $accessToken")
//                            AuthReply.Success(accessToken)
//                        } else {
//                            AuthReply.Failed("Failed to retrieve token")
//                        }
//                    } catch (e: Exception) {
//                        AuthReply.Failed("Error retrieving token: ${e.message}")
//                    }
//                } else {
//                    AuthReply.Failed("Authorization code not found in intent")
//                }
//            } ?: AuthReply.Failed("Intent data is null")
//        }
//    }
}









