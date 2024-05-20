package com.mataecheverry.project_ravelry

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthReply
import com.mataecheverry.project_ravelry.ui.Aplicacio
import com.mataecheverry.project_ravelry.ui.theme.Project_RavelryTheme

class MainActivity : ComponentActivity() {
    private val authManager: AuthManager by lazy { AuthManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project_RavelryTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Aplicacio()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent){
        super.onNewIntent(intent)
        val authReply = authManager.handleIntent(intent)
        when (authReply){
            is AuthReply.Success -> {

            }
            is AuthReply.Failed -> {

            }
        }
    }
}
