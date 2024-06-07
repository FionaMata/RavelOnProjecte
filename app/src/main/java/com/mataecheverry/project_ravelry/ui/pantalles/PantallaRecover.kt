package com.mataecheverry.project_ravelry.ui.pantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import kotlinx.coroutines.launch


@Composable
fun PantallaRecover(authManager: AuthManager, snackbarHostState: SnackbarHostState, goToLogin: ()-> Unit){

    var userEmail by remember { mutableStateOf("") }
    val area = rememberCoroutineScope()


    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .background(Color(0XFFF3F4F0))
        .padding(top = 75.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(Modifier.weight(1F)){

        }
        Column (verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
  ) {
            TitleText(title = "Forgot your password?\nWe got your back.")
            HorizontalDivider(Modifier.padding(24.dp))
            DescriptionText(description = "Enter your email to start the password recovery process: ")
            Spacer(Modifier.padding(bottom=20.dp))

            TextField(
                value = userEmail,
                onValueChange = {userEmail = it},
                label = {Text("Your email")},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding (10.dp)
            )
            Spacer(Modifier.padding(bottom=20.dp))
            Button(onClick = {
                area.launch {
                    authManager.passwordForgotten(userEmail)
                    val result = snackbarHostState.showSnackbar(
                        message = "An email will be sent to you shortly. Check your email.",
                        actionLabel = "OK"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        goToLogin()
                    }
                }
            },
                colors = ButtonColors(
                    containerColor = Color(0XFF97EFE3),
                    contentColor = Color(0XFF000000),
                    disabledContainerColor = Color(0XFFF3F4F0),
                    disabledContentColor = Color(0XFFFFFFFF)
                )
            ){
                Text("Recover Password")
            }
            Spacer(Modifier.padding(bottom=20.dp))
            Button(onClick = {
                goToLogin()
            },
                colors = ButtonColors(
                    containerColor = Color(0XFF97EFE3),
                    contentColor = Color(0XFF000000),
                    disabledContainerColor = Color(0XFFF3F4F0),
                    disabledContentColor = Color(0XFFFFFFFF)
                )
            ){
                Text("Return to Login")
            }
            Spacer(Modifier.padding(160.dp))
            Text("Powered by Google Firebase and Institut Montilivi",
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        emojiSupportMatch = EmojiSupportMatch.Default
                    )
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}