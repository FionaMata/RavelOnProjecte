package com.mataecheverry.project_ravelry.ui.pantalles

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.mataecheverry.project_ravelry.MainActivity
import com.mataecheverry.project_ravelry.R
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthReply
import com.mataecheverry.project_ravelry.dades.autenticacio.LoggedInUser
import com.mataecheverry.project_ravelry.ui.AppDisplay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

//Caldrà fer el procediment del login per open id aqui!
@Preview
@Composable
fun PreviewLogin()
{
    AppDisplay {
        PantallaLogin(
            mainActivity = MainActivity(),
            authManager = AuthManager(LocalContext.current),
            goToRegister = { /*TODO*/ },
            goToRecover = { /*TODO*/ },
            goToHome = { /*TODO*/}
        )
    }
}


@Composable
fun PantallaLogin(
    mainActivity: MainActivity,
    authManager: AuthManager,
    goToRegister: () -> Unit,
    goToRecover: () -> Unit,
    goToHome: () -> Unit,
) {

    var email by remember { mutableStateOf(LoggedInUser.userMail) }
    var password by remember {mutableStateOf(LoggedInUser.userPassword)}
    var checked by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }
    var errorMessege by remember { mutableStateOf("") }
    val context = LocalContext.current
    val area = rememberCoroutineScope()



    val launcherIniciDeSessioAmbGoogle = rememberLauncherForActivityResult(
        //Llencem un activityForResult
        contract = ActivityResultContracts.StartActivityForResult()) { result ->
        //GoogleSignIn.getSignedInAccountFromIntent(result.data)) obre una activity de Google on
        //L'usuari es valida, i ens retornarà una resposta que contindrà el compte de google o un error
        when(val reply = authManager.manageGoogleLoginResults(GoogleSignIn.getSignedInAccountFromIntent(result.data))) {
            is AuthReply.Success -> {
                //Del compte de google volem les credencials, per tal de poder iniciar sessió amb Firebase
                val credentials = GoogleAuthProvider.getCredential(reply.dades.idToken, null)
                LoggedInUser.initialize(credentials)
                LoggedInUser.userToken = reply.dades.idToken.toString()
                area.launch {
                    val usuariFirebase = authManager.iniciDeSessioAmbCredencials(credentials)
                    if (usuariFirebase != null){
                        goToHome()
                    }
                }
            }
            is AuthReply.Failed -> {
                error = true
                errorMessege = reply.errorMessage
            }
            else -> {
                error = true
                errorMessege = "Unexpected error."
            }
        }
    }








    Column(
        modifier = Modifier
            .background(Color(0XFFF3D194))
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(0.4F)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoravelry),
                contentDescription = "Logo Ravelry",
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .padding(bottom = 35.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(25.dp)
                .weight(1F)
                .background(Color(0XFFF3F4F0))

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    label = { Text(text = "Correu electrònic") },
                    value = "",
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {
                        LoggedInUser.userMail = it
                        error = false
                        errorMessege = ""
                    },

                    )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    label = { Text(text = "Password") },
                    value = "",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {
                        LoggedInUser.userPassword = it
                        error = false
                        errorMessege = ""
                    },
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(text = "I Forgot my password :(",
                            Modifier
                                .clickable {
                                    goToRecover()
                                }
                                .padding(horizontal = 20.dp),
                            textDecoration = TextDecoration.Underline
                        )

                        Row(
                            Modifier.padding(horizontal = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = checked, onCheckedChange = {checked = it})
                            Text("Remember me")
                        }
                    }
                    Button(
                        onClick = {
                                  area.launch {
                                      emailAndPasswordLogin(
                                          authManager,
                                          email,
                                          password,
                                          goToHome
                                      )
                                      goToHome()
                                  }
                        },
                        colors = ButtonColors(
                            containerColor = Color(0XFF97EFE3),
                            contentColor = Color(0XFF000000),
                            disabledContainerColor = Color(0XFFF3F4F0),
                            disabledContentColor = Color(0XFFFFFFFF)
                        )
                    ) {
                        Text("Log in")
                    }
                }
                HorizontalDivider(Modifier.padding(10.dp))
                BotoXXSS(
                    onClick = {
                              authManager.iniciDeSessioAmbGoogle(launcherIniciDeSessioAmbGoogle)
                              },
                    icon = R.drawable.google,
                    nomXarxa = "Google")

                BotoXXSS(
                    onClick = { /*TODO*/ },
                    icon = R.drawable.logotipodeopenid,
                    nomXarxa = "OpenId"
                )
                HorizontalDivider(Modifier.padding(10.dp))
                Column(verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()){
                    Text(
                        "Not a member yet? Get started and register!",
                        modifier = Modifier
                            .padding(start = 20.dp,bottom=10.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End){
                        Button(
                            onClick = { goToRegister() },
                            colors = ButtonColors(
                                containerColor = Color(0XFF97EFE3),
                                contentColor = Color(0XFF000000),
                                disabledContainerColor = Color(0XFFF3F4F0),
                                disabledContentColor = Color(0XFFFFFFFF)
                            )
                        ) {
                            Text(text = "Sign on")
                        }
                    }
                }
            }
            //Box que ajuda a construir bé el format de la pàgina.
            Box(
                modifier = Modifier
                    .weight(0.4F)
            ) {
            }
        }
    }
}




@Composable
//Requereix l'icona del servei al que representa.
fun BotoXXSS(onClick: () -> Unit, icon: Int, nomXarxa: String) {
    var click by remember { mutableStateOf(false) }
    Surface(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .clickable { click = !click },
        shape = RoundedCornerShape(50),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onBackground),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier.size(24.dp),
                contentDescription = "Botó $nomXarxa",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign in with $nomXarxa", color = MaterialTheme.colorScheme.onBackground)
            click = true
        }
    }
}

//Suspend functions:

suspend fun emailAndPasswordLogin(
    authManager: AuthManager,
    email: String,
    password: String,
    goToStart: () -> Unit,
){
    if (email.isNotEmpty() && password.isNotEmpty()){
        when ( val reply = authManager.iniciaUsuariAmbCorreuIMotDePas(email, password)) {
            is AuthReply.Success -> {
                goToStart()
                val firebaseUser = reply.dades
                val idToken = firebaseUser?.getIdToken(true)?.await()
                LoggedInUser.initialize(email,password)
                LoggedInUser.userToken = idToken?.token.toString()
                goToStart()
            }
            is AuthReply.Failed ->  {}
        }
    }
}

