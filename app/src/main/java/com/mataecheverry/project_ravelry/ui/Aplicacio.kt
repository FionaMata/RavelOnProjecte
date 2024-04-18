package com.mataecheverry.project_ravelry.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mataecheverry.project_ravelry.R
import com.mataecheverry.project_ravelry.dades.app_models.AppUser
import com.mataecheverry.project_ravelry.dades.nav.Destinacio
import com.mataecheverry.project_ravelry.dades.nav.NavigationCat
import com.mataecheverry.project_ravelry.dades.nav.NavigationGraph
import com.mataecheverry.project_ravelry.ui.theme.Project_RavelryTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//region llistes
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

val displaysWithBottomAppBar = listOf(
    Destinacio.Home,
    Destinacio.Patterns,
    Destinacio.PatternDetails,
    Destinacio.Projects,
    Destinacio.ProjectDetails,
    Destinacio.Profile
)

//endregion


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
    val currentScreen by remember { mutableStateOf<Destinacio>(Destinacio.Home) }

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
            if (displaysWithBottomAppBar.contains(currentScreen)){
                BottomAppBar (
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    contentPadding = BottomAppBarDefaults.ContentPadding,
                ){
                    Row(horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()

                    ){
                        when(currentScreen) {
                            Destinacio.Home -> {
                                Icon(
                                    painterResource(id = R.drawable.search),
                                    contentDescription = "Icona d'una lupa :)",
                                    modifier = Modifier
                                        .size(25.dp))
                                Icon(
                                    painterResource(id = R.drawable.plus),
                                    contentDescription = "Icona d'un plus",
                                    modifier = Modifier
                                        .size(25.dp))
                            }
                            Destinacio.Patterns -> {
                                Icon(
                                    painterResource(id = R.drawable.search),
                                    contentDescription = "Icona d'una lupa :)")
                            }
                            Destinacio.PatternDetails -> {
                                Icon(
                                    painterResource(id = R.drawable.home),
                                    contentDescription = "Icona d'una casa")
                                Icon(
                                    painterResource(id = R.drawable.heart),
                                    contentDescription = "Icona d'una lupa :)")
                                Icon(
                                    painterResource(id = R.drawable.read),
                                    contentDescription = "Read Icon")
                            }
                            Destinacio.Projects -> {
                                Icon(
                                    painterResource(id = R.drawable.home),
                                    contentDescription = "Read Icon")
                                Icon(
                                    painterResource(id = R.drawable.search),
                                    contentDescription = "Magnifying glass")
                            }
                            Destinacio.ProjectDetails -> {
                                Icon(
                                    painterResource(id = R.drawable.home),
                                    contentDescription = "Home Icon")
                                Icon(
                                    painterResource(id = R.drawable.search),
                                    contentDescription = "Magnifying glass")
                                Icon(
                                    painterResource(id = R.drawable.heart),
                                    contentDescription = "Favorite Icon")
                            }
                            Destinacio.Profile -> {
                                Icon(
                                    painterResource(id = R.drawable.share),
                                    contentDescription = "Share icon")
                            }

                            else -> {}
                        }
                    }
                }

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
                drawerTonalElevation = 15.dp,
                windowInsets = WindowInsets(left = 35.dp, right = 35.dp, top = 48.dp) // És el padding
            ){
//                AsyncImage (
//                    //Aqui ha d'anar la imatge d'usuari. tirarem d'un async de moment
//                    model = appUser.large_photo_url,
//                    contentDescription = "User selected profile picture.",
//                    modifier = Modifier.fillMaxWidth()
//                        .clip(RoundedCornerShape(50.dp))
//                        .weight(0.2F))
                Spacer (Modifier.height(10.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSecondaryContainer,modifier= Modifier.height(15.dp))
                Spacer (Modifier.height(10.dp))
                NavigationCat.entries.forEach(){
                    NavigationDrawerItem  (
                        label = { Text(it.previousPath) },
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