package com.mataecheverry.project_ravelry.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
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

@Preview
@Composable
fun Aplicacio(){
    val controladorDeNavegacio = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var estatDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navBackStackEntry by controladorDeNavegacio.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route ?: ""
    val snackbarHostState = remember{ SnackbarHostState()}

    RavelryScaffold(rutaActual, snackbarHostState)


}


@Composable
fun RavelryScaffold(
//    controladorDeNavegacio: NavHostController,
//    coroutineScope: CoroutineScope,
//    estatDrawer: DrawerState,
    rutaActual: String,
    snackbarHostState: SnackbarHostState
){

    val topAppBarTitle = ""
    /**Posar TITOLS ADEQUATS A TRAVÉS DE RUTES*/

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = Text("Show snackbar"),
                icon = Icons.Filled.Face, contentDescription = "Icona carona",


                ) {
                
            }
        }

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