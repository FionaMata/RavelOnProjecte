package com.mataecheverry.project_ravelry.ui.pantalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mataecheverry.project_ravelry.R

@Preview
@Composable
fun PantallaDetall(){

    val projectTitle by remember { mutableStateOf("")}
    val creator by remember { mutableStateOf("") }
    val publicationPlace by remember {mutableStateOf("")}
    val craft by remember {mutableStateOf("")}
    val publishedIn by remember {mutableStateOf("")}
    val suggestedYarn by remember {mutableStateOf("")}
    val yarnWeight by remember {mutableStateOf("")}
    val gauge by remember {mutableStateOf("")}
    val needleSize by remember {mutableStateOf("")}
    val yardage by remember {mutableStateOf("")}
    val sizesAvailable by remember {mutableStateOf("")}
    val language by remember {mutableStateOf("")}
    val free by remember {mutableStateOf(false)}
    val description by remember { mutableStateOf(" ") }
    val context = LocalContext.current


    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .background(Color(0XFFF3F4F0))
        .padding(top=75.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(){
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data( "https://images4-a.ravelrycache.com/uploads/Pauknitte/994458875/thumbnail_image1__1__medium2.jpg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = "EXEMPLE imatge jersei",
                contentScale = ContentScale.FillBounds

            )
            FloatingActionButton(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.BottomEnd)
                    .padding(5.dp),
                onClick = {},
                containerColor = Color(0XFF19444D),
                shape = RoundedCornerShape(9.dp),
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 25.dp,
                    pressedElevation = 0.dp)){
                Image(painter = painterResource(id = R.drawable.campetita),
                    contentDescription = "Icon of a small camera.",
                    Modifier.size(20.dp))
            }
        }

            Column(){
                TitleText(title = projectTitle)
                DescriptionText(description = creator)
                DescriptionText(description = publicationPlace)
                DescriptionText(description = publishedIn)

                Divider(
                    Modifier
                        .padding(5.dp)
                        .background(Color(0XFFEE6E62)))

                TitleText(title = "Craft")
                DescriptionText(description = craft)
                DescriptionText(description = suggestedYarn)
                DescriptionText(description = yarnWeight)
                DescriptionText(description = needleSize)
                DescriptionText(description = gauge)
                DescriptionText(description = yardage)

                Divider(
                    Modifier
                        .padding(5.dp)
                        .background(Color(0XFFEE6E62)))

                TitleText(title = "More")
                DescriptionText(description = language)
                DescriptionText(description = sizesAvailable)
                if (free){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Download Free Here")
                    }
                } else {
                    DescriptionText("Download available on website.")
                }

                DescriptionText(description = description,)
            }


    }
}

//Funció que fem servir per crear les capçaleres dels detalls:

@Composable
//title: String
fun TitleText(title: String){
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color(0XFF19444D),
        modifier = Modifier
            .padding(horizontal = 10.dp))
}
//Funció que fem servir per crear el contingut posterios a les capçaleres dels detalls:
@Composable
fun DescriptionText(description: String){
    Text(text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0XFF19444D),
        modifier = Modifier
            .padding(horizontal = 10.dp)
        )
}


