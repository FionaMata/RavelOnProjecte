package com.mataecheverry.project_ravelry.ui.pantalles

import android.app.Application
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.mataecheverry.project_ravelry.MainActivity
import com.mataecheverry.project_ravelry.R

//Caldrà fer el procediment del login per open id aqui!


@Composable
fun PantallaLogin(mainActivity: MainActivity) {


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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {

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

                                }
                                .padding(horizontal = 20.dp),
                            textDecoration = TextDecoration.Underline
                        )

                        Row(
                            Modifier.padding(horizontal = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = false, onCheckedChange = {})
                            Text("Remember me")
                        }
                    }
                    Button(
                        onClick = { /*TODO*/ },
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
                BotoXXSS(onClick = { /*TODO*/ }, icon = R.drawable.google, nomXarxa = "Google")
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
                            onClick = { /*TODO*/ },
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

