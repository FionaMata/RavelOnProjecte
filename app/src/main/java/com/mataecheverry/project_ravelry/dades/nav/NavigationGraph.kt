package com.mataecheverry.project_ravelry.dades.nav

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseUser
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.xarxa.firebase.FirestoreManager
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaHome
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaLogin
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaRecover
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaRegistry

@Composable
fun NavigationGraph (
    navigationController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState) {
    val authManager = AuthManager(LocalContext.current)
    val firestoreManager = FirestoreManager(LocalContext.current)
    var user: FirebaseUser? by remember { mutableStateOf<FirebaseUser?>(null) }

    LaunchedEffect(key1 = Unit) {
        user = authManager.getUser()
    }

    //region variables:

    val goToLogin = {
        navigationController.navigate(Destinacio.Login.genericPath) {
            popUpTo(Destinacio.Login.genericPath) { inclusive = false }
        }
    }

    val goToRegister = {
        navigationController.navigate(Destinacio.Register.genericPath) {
            popUpTo(Destinacio.Login.genericPath) { inclusive = false }
        }
    }

    val recoverUserInformation = {
        navigationController.navigate(Destinacio.Recover.genericPath) {
            popUpTo(Destinacio.Login.genericPath) { inclusive = false }
        }
    }

    val goToHome = {
        navigationController.navigate(Destinacio.Home.genericPath) {
            popUpTo(Destinacio.Login.genericPath) { inclusive = false }
        }
    }

    val goBack: () -> Unit = { navigationController.popBackStack() }
    //endregion


    NavHost(navController = navigationController,
        startDestination = if (user == null)
                                NavigationCat.Login.previousPath
                            else
                                NavigationCat.Home.previousPath

    ) {

        navigation(
            startDestination = Destinacio.Home.basePath,
            route = NavigationCat.Home.previousPath)
        {
            composable(
                route = Destinacio.Home.genericPath
            ){
                PantallaHome(authManager, goToLogin, firestoreManager, onClick ={})
            }
        }

        navigation(
            startDestination = Destinacio.Login.basePath,
            route = NavigationCat.Login.previousPath)
        {
            composable(route = Destinacio.Login.genericPath){
                PantallaLogin(authManager, androidx.lifecycle.viewmodel.compose.viewModel(), goToRegister, recoverUserInformation, goToHome)
            }
            composable(route = Destinacio.Register.genericPath){
                PantallaRegistry(authManager, goBack, goToHome)
            }
            composable(route = Destinacio.Recover.genericPath){
                PantallaRecover(authManager, snackbarHostState, goToLogin) //authManager, goToLogin
            }
            composable(route = Destinacio.Home.genericPath){
                PantallaHome(authManager, goToLogin, firestoreManager, onClick = {})
            }
        }


    }
}