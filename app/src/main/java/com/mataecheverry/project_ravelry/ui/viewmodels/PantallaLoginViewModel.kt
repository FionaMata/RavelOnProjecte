package com.mataecheverry.project_ravelry.ui.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthReply
import com.mataecheverry.project_ravelry.dades.autenticacio.CALLBACK
import com.mataecheverry.project_ravelry.dades.autenticacio.FirestoreManager
import com.mataecheverry.project_ravelry.dades.xarxa.api.RavelryClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class PantallaLoginViewModel: ViewModel() {

    private var _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()
    var firestoreManager: FirestoreManager = FirestoreManager()

    fun handleAuthResponse(intent: Intent, onSuccess: (String)-> Unit, onError: (String)-> Unit){
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(CALLBACK)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                viewModelScope.launch {
                    when (val tokenReply = RavelryClient.exchangeToken(code)) {
                        is AuthReply.Success -> {
                            _state.value = state.value.copy(isSuccessful = true)
                            onSuccess(tokenReply.dades.accessToken)
                        }
                        is AuthReply.Failed -> {
                            onError(tokenReply.errorMessage)
                        }
                    }
                }
            } else {
                onError("Authorization code not found")
            }
        } else {
            onError("Invalid intent data")
        }
    }
}



data class LoginState(
    var isSuccessful: Boolean = false
)

