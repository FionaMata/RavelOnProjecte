package com.mataecheverry.project_ravelry.dades.autenticacio

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await


sealed class AuthReply<out T>{
    data class Success<T>(val dades: T): AuthReply<T>()
    data class Failed (val errorMessage: String): AuthReply<Nothing>()
}

class AuthManager (private val context: Context){
    private val firebaseAuth: FirebaseAuth by lazy { com.google.firebase.Firebase.auth }


    private val scope = "offline patternstore-read patternstore-pdf"
    private val state = "request state"
    private var projectAccessToken: String = ""


    private val googleClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("713844823887-n1iqrilqvinlo1l3v85kir0j3vj4kp1q.apps.googleusercontent.com")
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)

    }

    fun getUser(): FirebaseUser?{
        return firebaseAuth.currentUser
    }

    fun tancaSessio(){
        firebaseAuth.signOut()
    }

    //region MAIL I PASSWORD
    //No podem fer un login amb correu i mot de pas si no està registrat prèviament:
    suspend fun createUserWithMailAndPassword(correu: String,
                                             motDePas: String): AuthReply<FirebaseUser?> {
        return try{
            val resultat = firebaseAuth.createUserWithEmailAndPassword(correu, motDePas).await()
            AuthReply.Success(resultat.user?: throw Exception ("EXCEPTION DE CREAR USUARI AMB CORREU I PASSWORD"))
        }catch (e:Exception){
            AuthReply.Failed(e.message?:"CATCH DE CREACIO D'USUARI NOU.")
        }
    }

    //Inici de sessió amb correu i contrasenya:
    suspend fun iniciaUsuariAmbCorreuIMotDePas(correu: String,
                                               motDePas: String): AuthReply<FirebaseUser?> {
        return try{
            val resultat = firebaseAuth.signInWithEmailAndPassword(correu, motDePas).await()
            AuthReply.Success(resultat.user?: throw Exception ("Something went wrong. Check username and password - LIADA 1 EN INICI DE SESSIO"))
        }catch (e:Exception){
           AuthReply.Failed(e.message?:"Something failed while Signing in - FALLO INICI SESSIO. ESTEM AL CATCH")
        }
    }
    //endregion

    //region Google
    suspend fun iniciDeSessioAmbCredencials(credencial: AuthCredential): AuthReply<FirebaseUser>? {
        return try {
            val usuariDeFirebase = firebaseAuth.signInWithCredential(credencial).await()
            usuariDeFirebase.user?.let {
                AuthReply.Success(it)
            } ?: throw Exception("THROW INICI SESSIO AMB GOOGLE")
        } catch (e: Exception) {
            AuthReply.Failed(e.message ?: "CATCH INICI DE SESSIO AMB GOOGLE")
        }
    }

    fun iniciDeSessioAmbGoogle(laucherIniciDeSessioAmbGoogle: ActivityResultLauncher<Intent>) {
        val signInIntent = googleClient.signInIntent
        laucherIniciDeSessioAmbGoogle.launch(signInIntent)
    }

    fun manageGoogleLoginResults(task: Task<GoogleSignInAccount>): AuthReply<GoogleSignInAccount>? {
        return try {
            val compte = task.getResult(ApiException::class.java)
            AuthReply.Success(compte)
        } catch (e: ApiException) {
            AuthReply.Failed(e.message ?: "No s'ha pogut iniciar sessió amb Google")
        }
    }
    //endregion

    //region OPENID

    // Function to sign in with Ravelry credentials
//    suspend fun signInOpenId(): AuthReply<FirebaseUser>? {
//        return try {
//            val credential: AuthCredential? = getOpenIdUserCredentials()
//            val firebaseUser = credential?.let {
//                firebaseAuth.signInWithCredential(it).await().credential }
//            firebaseUser?.let {
//                AuthReply.Success(it)
//            } ?: throw Exception("Failed to sign in with Ravelry credentials")
//        } catch (e: Exception) {
//            AuthReply.Failed(e.message ?: "Failed to sign in with Ravelry credentials")
//        }
//    }
//
//
//
//    //Aconsegueix OpenIdCredentials
//    private suspend fun getOpenIdUserCredentials(): AuthCredential? {
//        var credential: AuthCredential? = null
//        val providerBuilder = OAuthProvider.newBuilder("oidc.ravelry")
//        providerBuilder.addCustomParameter("login_hint", "fionamata@gmail.com")
//
//        return try {
//            // Start the sign-in flow with Ravelry provider
//            val pendingAuthResult = firebaseAuth.pendingAuthResult
//            val pendingCredential = pendingAuthResult?.await()?.credential
//            pendingCredential ?: firebaseAuth.startActivityForSignInWithProvider(MainActivity@this, providerBuilder.build()).await().credential
//        } catch (e: Exception) {
//            // Handle any exceptions
//            null
//        }
//
//
//        return credential
//    }

    //endregion






}

