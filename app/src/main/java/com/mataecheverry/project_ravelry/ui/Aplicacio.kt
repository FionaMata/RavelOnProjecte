package com.mataecheverry.project_ravelry.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mataecheverry.project_ravelry.ui.theme.Project_RavelryTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun Aplicacio(){
    val controladorDeNavegacio = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var estatDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navBackStackEntry by controladorDeNavegacio.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route ?: ""
    val snackbarHostState = remember{ SnackbarHostState()}

    RavelryScaffold(controladorDeNavegacio, coroutineScope, estatDrawer, rutaActual, snackbarHostState)


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavelryScaffold(
    controladorDeNavegacio: NavHostController,
    coroutineScope: CoroutineScope,
    estatDrawer: DrawerState,
    rutaActual: String,
    snackbarHostState: SnackbarHostState
){

    val topAppBarTitle = ""
    /**Posar TITOLS ADEQUATS A TRAVÉS DE RUTES*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(topAppBarTitle)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary)
            )
        },
//        navigationIcon = {
//            if (rutaActual in listOf(
//                    Destinacio.Portada.rutaGenerica,
//                    Destinacio.Fotografies.rutaGenerica,
//                    Destinacio.Preferencies.rutaGenerica,
//                    Destinacio.QuantA.rutaGenerica,
//                    Destinacio.Preferits.rutaGenerica
//                )
//            //Destinacio.Partida.rutaGenerica,
//            ) {
//
//                IconButton(onClick = {
//                    coroutineScope.launch {
//                        if (estatDrawer.isClosed)
//                            estatDrawer.open()
//                        else
//                            estatDrawer.close()
//                    }
//                }) {
//
//                    Icon(
//                        imageVector = Icons.Default.Menu,
//                        contentDescription = null,
//                        tint = MaterialTheme.colorScheme.onPrimary
//                    )
//                }
//            } else {
//                IconButton(onClick = { controladorDeNavegacio.navigateUp() }) {
//
//                    Icon(
//                        imageVector = Icons.Rounded.ArrowBack,
//                        contentDescription = null,
//                        tint = MaterialTheme.colorScheme.onPrimary
//                    )
//                }
//            }
//      }
        bottomBar = {
                    BottomAppBar {

                    }
        },

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {Text("Snaki snsack snack")},
                icon = {Icon(Icons.Filled.Face,
                    contentDescription = "Carona d'exemple")},
                onClick = {
                    coroutineScope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = "snackisnak!",
                                actionLabel = "snak!",
                                duration = SnackbarDuration.Short
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                //QUAN FEM AIXÒ
                            }

                            SnackbarResult.Dismissed -> {
                                //QUAN HO DEIXEM DE FER
                            }
                        }
                    }
                }
            )
        },


    ) {
        Modifier.padding(it)
    }


}


@Composable
fun RavelryDrawer(
    controladorDeNavegacio: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    estatDrawer: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    modifier: Modifier,
    rutaActual: String

){

}