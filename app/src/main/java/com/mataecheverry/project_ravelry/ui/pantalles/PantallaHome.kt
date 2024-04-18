package com.mataecheverry.project_ravelry.ui.pantalles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.xarxa.firebase.FirestoreManager
import com.mataecheverry.project_ravelry.ui.AppDisplay


@Preview()
@Composable
private fun HomePreview() {
    AppDisplay {
        PantallaHome(
            authManager = AuthManager(LocalContext.current),
            goToLogin = {},
            firestoreManager = FirestoreManager(LocalContext.current)
        )
    }
}

@Composable
fun PantallaHome(authManager: AuthManager, goToLogin:() -> Unit, firestoreManager: FirestoreManager) {

    //var selected = remember {mutableStateOf(true)}
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Debut Patterns", "Hot Right Now", "You've Looked At...")

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background)
    ){
        TabRow(tabIndex){
            tabs.forEachIndexed{ index , title ->
                Tab(text = {Text(title) },
                    selected = tabIndex == index,
                    onClick = {tabIndex = index }
                )
            }
        }
        when (tabIndex){
            //0 -> Carregar contingut DebutPatterns
            //1 -> Carregar contingut HotRightNow
            //2 -> Carregar contingut YouveLookedAt
        }

    }


}



@Preview
@Composable
fun OutlinedRavelCard() {
    OutlinedCard(
        Modifier
            .requiredSize(200.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.outlinedCardElevation(10.dp, 0.dp, 15.dp, 14.dp)
        ) {
        AsyncImage(
           model = "https://en.wikipedia.org/wiki/Yarn#/media/File:Yarn_at_Folklife_-_Stierch.jpg",//project.first_photo,
            contentDescription = "Project thumbnail",
            modifier = Modifier.size(150.dp)
        )
        Column(Modifier.padding(horizontal = 15.dp)){
            Text(text="project.patternName",
                color = Color(0XFF19444D),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text("project.user.username",
                color = Color(0XFF19444D),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light)
        }

    }

}