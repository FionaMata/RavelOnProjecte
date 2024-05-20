package com.mataecheverry.project_ravelry.dades.autenticacio

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.mataecheverry.project_ravelry.dades.xarxa.api.RavelryClient
import com.mataecheverry.project_ravelry.dades.xarxa.api.auth.RavelryAuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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


    suspend fun iniciDeSessioAmbRavelry(launcherIniciDeSessioAmbRavelry: ActivityResultLauncher<Intent>) {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "$URL_AUTH?client_id=$CLIENT_ID&scope=offline&redirect_uri=${RavelryClient.redirectURI}"
            )
        )
        launcherIniciDeSessioAmbRavelry.launch(intent)


    }

    fun handleIntent(intent: Intent): AuthReply<String> {
        intent.data?.let { uri ->
            val code = uri.getQueryParameter("code")
            return if (code != null) {
                AuthReply.Success(code)

                val builder = Retrofit.Builder()
                    .baseUrl("https://www.ravelry.com/")
                    .addConverterFactory(GsonConverterFactory.create())

                val retrofit = builder.build()
                val ravClient: RavelryAuthService = retrofit.create(RavelryAuthService::class.java)

                runBlocking {
                    try{
                        val response = ravClient.getAccessToken(
                            clientId = CLIENT_ID,
                            clientSecret = CLIENT_SECRET,
                            code = "code",
                            redirectUri = RavelryClient.redirectURI
                            )
                        if (response.isSuccessful){
                            val accessToken = response.body()?.accessToken ?: "No token received"
                            AuthReply.Success(accessToken)
                        } else {
                            AuthReply.Failed("Failed to retrieve token")
                        }
                    } catch (e: Exception) {
                        AuthReply.Failed("Error retrieving token: ${e.message}")
                    }
                }
            } else {
                AuthReply.Failed("Authorization code not found in intent")
            }
        } ?: return AuthReply.Failed("Intent data is null")
    }


//region
    //Si no funciona amb launcherRavelry: Activity, li passem la MainActivity().
//    suspend fun signInRavelryOpenId(launcherRavelry: ManagedActivityResultLauncher<Intent, ActivityResult>) {
//
//        val pendingResultTask = firebaseAuth.pendingAuthResult
//
//        if (pendingResultTask != null) {
//            pendingResultTask.await()
//            //Log.d("LoggedInUser", AppUser.LoggedInUser.email)
//
//            AuthReply.Success(firebaseAuth.currentUser!!)
//
//        } else {
//            val oidcProvider = OAuthProvider.newBuilder("oidc.ravelry")
//                .addCustomParameter("login_hint", "user@example.com")
//                .build()
//            //Fem anar el listener, com a equivalent del .await()
//            firebaseAuth.startActivityForSignInWithProvider(MainActivity(), oidcProvider)
//                .addOnSuccessListener {
//
//                    val firebaseUser = it.user
//                    Log.d("LoggedInUser", firebaseUser?.displayName.toString())
//                    AuthReply.Success(it.user)
//                }
//                .addOnFailureListener {oi900
//                    AuthReply.Failed("Error logging in with Ravelry. $it.message")
//                }
//        }
//
//    }
//
//    fun manageRavleryLoginResult(result: ActivityResult) {
//        val task = firebaseAuth.pendingAuthResult
//        if (task != null) {
//            task.addOnSuccessListener {
//                AuthReply.Success(it)
//            }
//            task.addOnFailureListener {
//                AuthReply.Failed("Could not retrieve de User. Something went wrong :( ${it.localizedMessage}")
//            }
//        } else {
//            AuthReply.Failed("PendingAuthResult is null :(")
//        }
//    }
}

//endregion








