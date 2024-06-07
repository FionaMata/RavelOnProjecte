package com.mataecheverry.project_ravelry.ui.viewmodels

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.mataecheverry.project_ravelry.dades.autenticacio.CALLBACK
import com.mataecheverry.project_ravelry.dades.autenticacio.CLIENT_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PantallaLoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    private val firebaseAuth: FirebaseAuth by lazy {
        Firebase.auth
    }

    fun startSignInWithRavelry(launcher: ActivityResultLauncher<Intent>) {
        val loginUrl = "https://www.ravelry.com/oauth2/auth"
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "$loginUrl?response_type=code&client_id=$CLIENT_ID&redirect_uri=$CALLBACK&state=sheepbaa&scope=offline"
            )
        )
        launcher.launch(intent)
    }

//    fun handleAuthResponse(intent: Intent, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
//        val uri = intent.data
//        if (uri != null && uri.toString().startsWith(CALLBACK)) {
//            val code = uri.getQueryParameter("code")
//            if (code != null) {
//                viewModelScope.launch {
//                    when (val tokenReply = RavelryClient.exchangeToken(code)) {
//                        is AuthReply.Success -> {
//                            _state.value = LoginState(isSuccessful = true)
//                            onSuccess(tokenReply.dades.accessToken)
//                        }
//
//                        is AuthReply.Failed -> {
//                            onError(tokenReply.errorMessage)
//                        }
//                    }
//                }
//            } else {
//                AuthReply.Failed("Authorization code not found")
//            }
//        } else {
//            AuthReply.Failed("Invalid intent data")
//        }
//    }
}

data class LoginState(
    val isSuccessful: Boolean = false,
    val accessToken: String? = null,
    val error: String? = null
)

