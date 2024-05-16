package com.mataecheverry.project_ravelry.ui.pantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.xarxa.firebase.FirestoreManager
import com.mataecheverry.project_ravelry.ui.viewmodels.ViewModelPatterns



@Composable
fun PantallaFavorites (
    authManager: AuthManager,
    goToHome:() -> Unit,
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
