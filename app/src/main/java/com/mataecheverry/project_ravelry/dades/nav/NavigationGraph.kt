package com.mataecheverry.project_ravelry.dades.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.firebase.auth.FirebaseUser
import com.mataecheverry.project_ravelry.MainActivity
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.xarxa.firebase.FirestoreManager
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaHome
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaLogin
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaPatterns
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaRegistry
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaSearch
import com.mataecheverry.project_ravelry.ui.pantalles.PantallaShops

@Composable
fun NavigationGraph (
    navigationController: NavHostController = rememberNavController())
{
    val authManager = AuthManager(LocalContext.current)
    val firestoreManager = FirestoreManager()
    val user: FirebaseUser? = authManager.getUser()


    NavHost(
        navController = navigationController,
        startDestination =
            if (user == null)
                NavigationCat.Login.previousPath
            else
               Destinacio.Home.genericPath

    ){
        navigation(
            startDestination = Destinacio.Login.genericPath,
            NavigationCat.Login.previousPath
        ) {
            composable(route = Destinacio.Login.genericPath) {
                PantallaLogin(
                    mainActivity = MainActivity(),
                    authManager = authManager,
                    goToRegister = {
                        navigationController.navigate(Destinacio.Register.genericPath)
                        {
                            popUpTo(Destinacio.Login.genericPath)
                            { inclusive = false }
                        }
                    },
                    goToRecover = {
                        navigationController.navigate(Destinacio.Recover.genericPath)
                        {
                            popUpTo(Destinacio.Login.genericPath)
                            { inclusive = false }
                        }
                    },
                    goToStart = {
                        navigationController.navigate(Destinacio.Home.genericPath) {
                            popUpTo(Destinacio.Login.genericPath)
                            { inclusive = false }
                        }
                    }
                )
            }

            composable(route = Destinacio.Register.genericPath) {
                PantallaRegistry(
                    authManager = authManager,
                    goBack = {
                        navigationController.navigate(Destinacio.Register.genericPath)
                        {
                            popUpTo(Destinacio.Login.genericPath)
                            { inclusive = false }
                        }
                    },
                    goToHome = {
                        navigationController.navigate(Destinacio.Recover.genericPath) {
                            popUpTo(Destinacio.Login.genericPath) { inclusive = false }
                        }
                    }
                )
            }

        }
        navigation(
            startDestination = Destinacio.Home.basePath,
            route = NavigationCat.Login.previousPath
        ){
            composable(Destinacio.Home.genericPath){
                PantallaHome()
            }
        }

        navigation(
            Destinacio.Patterns.basePath,
            NavigationCat.Patterns.previousPath
        ){
            composable(Destinacio.Patterns.genericPath){
                PantallaPatterns()
            }
        }

        navigation(
            Destinacio.Search.basePath,
            NavigationCat.Search.previousPath
        ){
            composable(Destinacio.Search.genericPath){
                PantallaSearch()
            }
        }

        navigation(
            Destinacio.Shops.basePath,
            NavigationCat.Shops.previousPath
        ){
            composable(Destinacio.Shops.genericPath){
                PantallaShops()
            }
        }
    }
}