package com.mataecheverry.project_ravelry.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mataecheverry.project_ravelry.dades.app_models.AppUser
import com.mataecheverry.project_ravelry.dades.nav.Destinacio
import com.mataecheverry.project_ravelry.dades.nav.NavigationCat
import com.mataecheverry.project_ravelry.dades.nav.NavigationGraph
import com.mataecheverry.project_ravelry.ui.theme.Project_RavelryTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val displaysWithouthDrawer = listOf(
    Destinacio.Login.genericPath,
    Destinacio.Register.genericPath,
    Destinacio.Recover.genericPath
)

val displaysWithDrawer = listOf(
    Destinacio.Home.genericPath,
    Destinacio.Search.genericPath,
    Destinacio.Patterns.genericPath,
    Destinacio.PatternDetails.genericPath,
    Destinacio.Favorites.genericPath,
    Destinacio.FavoriteDetails.genericPath,
    Destinacio.Projects.genericPath,
    Destinacio.ProjectDetails.genericPath,
    Destinacio.UploadProject.genericPath,
    Destinacio.Shops.genericPath,
    Destinacio.ShopDetails.genericPath,
    Destinacio.Calendar.genericPath,
    Destinacio.CalendarDetails.genericPath,
    Destinacio.About.genericPath
)
@Composable
fun AppDisplay (content: @Composable () -> Unit){
    Project_RavelryTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            content()
        }
    }
}


@Preview
@Composable
fun Aplicacio(content: @Composable ()-> Unit = {Text ("")}){
    val controladorDeNavegacio = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var estatDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navBackStackEntry by controladorDeNavegacio.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route ?: ""
    val snackbarHostState = remember{ SnackbarHostState()}
    val appUser = AppUser()

    RavelryScaffold(controladorDeNavegacio, coroutineScope, estatDrawer, rutaActual, snackbarHostState, appUser)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavelryScaffold(
    navigationController: NavHostController,
    coroutineScope: CoroutineScope,
    estatDrawer: DrawerState,
    currentPath: String,
    snackbarHostState: SnackbarHostState,
    appUser: AppUser
){
    var topAppBarTitle = ""
    /**Posar TITOLS ADEQUATS A TRAVÉS DE RUTES*/
    when (currentPath){
        Destinacio.Home.genericPath -> topAppBarTitle = stringResource(NavigationCat.Home.title)
        Destinacio.Favorites.genericPath -> topAppBarTitle = stringResource(NavigationCat.Favorites.title)
        Destinacio.Patterns.genericPath -> topAppBarTitle = stringResource(NavigationCat.Patterns.title)
        Destinacio.Search.genericPath -> topAppBarTitle = stringResource(NavigationCat.Search.title)
        Destinacio.Shops.genericPath -> topAppBarTitle = stringResource(NavigationCat.Shops.title)
        Destinacio.Register.genericPath -> topAppBarTitle = stringResource(NavigationCat.Register.title)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(topAppBarTitle)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary),
                navigationIcon = {
                    if (currentPath in displaysWithDrawer)
                    {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (estatDrawer.isClosed)
                                    estatDrawer.open()
                                else
                                    estatDrawer.close()
                            }
                        })
                        {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    } else {
                        IconButton(onClick = { navigationController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            ) },
        bottomBar = {
            BottomAppBar (
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ){Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "AQUI BOTONS LUPA I +")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        //De normal ha d'estar amagat
//        floatingActionButton = {
//            ExtendedFloatingActionButton(
//                text = {Text("Snaki snsack snack")},
//                icon = {Icon(Icons.Filled.Face,
//                    contentDescription = "Carona d'exemple")},
//                onClick = {
//                    coroutineScope.launch {
//                        val result = snackbarHostState
//                            .showSnackbar(
//                                message = "snackisnak!",
//                                actionLabel = "snak!",
//                                duration = SnackbarDuration.Short
//                            )
//                        when (result) {
//                            SnackbarResult.ActionPerformed -> {
//                                //QUAN FEM AIXÒ
//                            }
//
//                            SnackbarResult.Dismissed -> {
//                                //QUAN HO DEIXEM DE FER
//                            }
//                        }
//                    }
//                }
//            )
//        }
    ) {paddingValues ->
        RavelryDrawer(
            navigationController,
            coroutineScope,
            estatDrawer,
            modifier = Modifier.padding(paddingValues),
            rutaActual = currentPath,
            appUser = appUser)
    }
}


@Composable
fun RavelryDrawer(
    navigationController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    estatDrawer: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    modifier: Modifier,
    rutaActual: String,
    appUser: AppUser

){
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = estatDrawer,
        drawerContent = {
            ModalDrawerSheet (
                drawerShape = ShapeDefaults.Small, //fa referència a la mida del corner radius
                drawerContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                drawerContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                drawerTonalElevation = 5.dp,
                windowInsets = WindowInsets(left = 24.dp, right = 24.dp, top = 48.dp) // És el padding
            ){
                AsyncImage (
                    //Aqui ha d'anar la imatge d'usuari. tirarem d'un async de moment
                   model = appUser.large_photo_url,
                    contentDescription = "User selected profile picture.",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth)
                Spacer (Modifier.height(48.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSecondaryContainer,modifier= Modifier.height(15.dp))
                Spacer (Modifier.height(48.dp))
                NavigationCat.entries.forEach {
                    NavigationDrawerItem  (
                        label = { Text(it.title.toString()) },
                        selected = rutaActual.contains(it.previousPath),
                        //icon = {Icon (imageVector = it.icon, it.title)},
                        onClick = {
                            coroutineScope.launch {
                                estatDrawer.close()
                            }
                            navigationController.navigate(it.previousPath) {
                                popUpTo(navigationController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }  },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedBadgeColor = MaterialTheme.colorScheme.error,
                            unselectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            selectedBadgeColor = MaterialTheme.colorScheme.error,
                            selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer),
                        badge = {Icon(imageVector = Icons.Default.ArrowBack, null)},
                        shape = ShapeDefaults.Medium
                    )
                }
            }
        },
        gesturesEnabled = rutaActual in displaysWithDrawer
    ) {
        NavigationGraph(navigationController)
    }

}