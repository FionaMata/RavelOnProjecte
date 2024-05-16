package com.mataecheverry.project_ravelry.ui.pantalles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.xarxa.firebase.FirestoreManager
import com.mataecheverry.project_ravelry.models.app_models.AppPattern
import com.mataecheverry.project_ravelry.ui.viewmodels.ViewModelPatterns


@Composable
fun PantallaPatterns (
    authManager: AuthManager,
    goToLogin:() -> Unit,
    firestoreManager: FirestoreManager,
    viewModel: ViewModelPatterns = androidx.lifecycle.viewmodel.compose.viewModel(),
    onClick: (String) -> Unit)

    {
        val state = viewModel.state.collectAsState()
        val patterns = state.value.patterns

        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
        ){

            LazyVerticalGrid(columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)){
                items(patterns){
                    OutlinedRavelCard(it, onClick, viewModel)
                }
            }

        }
    }


@Composable
fun OutlinedRavelCard(appPattern: AppPattern, onClick: (String)-> Unit, viewModel: ViewModelPatterns) {

    OutlinedCard(
        onClick = {onClick(appPattern.id.toString())},
        Modifier
            .size(width = 240.dp, height = 150.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.outlinedCardColors(MaterialTheme.colorScheme.primaryContainer),
        border = BorderStroke(1.dp, Color.Transparent),
        elevation = CardDefaults.outlinedCardElevation(10.dp, 0.dp, 15.dp, 14.dp),
        content = {
            AsyncImage(
                model = appPattern.photos,  //0 = thumbnail
                contentDescription = "Project thumbnail",
                modifier = Modifier.size(150.dp)
            )
            Column(Modifier.padding(horizontal = 15.dp))
            {
                Text(text= appPattern.name,
                    color = Color(0XFF19444D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = appPattern.pattern_author.name,
                    color = Color(0XFF19444D),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                )
            }
        }
    )
}