package com.mataecheverry.project_ravelry.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mataecheverry.project_ravelry.R
import com.mataecheverry.project_ravelry.dades.autenticacio.AuthManager
import com.mataecheverry.project_ravelry.dades.nav.Destinacio
import com.mataecheverry.project_ravelry.dades.nav.NavigationCat
import com.mataecheverry.project_ravelry.dades.nav.NavigationGraph
import com.mataecheverry.project_ravelry.models.app_models.AppUser
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
    Destinacio.Favorites.genericPath,
    Destinacio.Projects.genericPath,
    Destinacio.Shops.genericPath,
    Destinacio.Calendar.genericPath,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Aplicacio(content: @Composable ()-> Unit = {})
{
    val controladorDeNavegacio = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    var estatDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navBackStackEntry by controladorDeNavegacio.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route ?: ""
    val snackbarHostState = remember{ SnackbarHostState()}
    val bottomBarState: MutableState<Boolean> = rememberSaveable {
        (mutableStateOf(true))
    }
    val authManager = AuthManager(LocalContext.current)

    RavelryScaffold(controladorDeNavegacio, coroutineScope, estatDrawer, rutaActual, bottomBarState, snackbarHostState, authManager)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavelryScaffold(
    navigationController: NavHostController,
    coroutineScope: CoroutineScope,
    estatDrawer: DrawerState,
    currentPath: String,
    bottomBarState: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState,
    authManager: AuthManager
){
    var topAppBarTitle = ""

    when (currentPath){
        Destinacio.Home.genericPath -> topAppBarTitle = stringResource(NavigationCat.Home.title)
        Destinacio.Recover.genericPath -> topAppBarTitle = stringResource(NavigationCat.Recover.title)
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
                    contentPadding = BottomAppBarDefaults.ContentPadding,

                ){
                    Row(horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()

                    ){
                        when(currentPath) {
                            Destinacio.Home.genericPath ->
                                {
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
                            Destinacio.Patterns.genericPath -> {
                                Icon(
                                    painterResource(id = R.drawable.search),
                                    contentDescription = "Icona d'una lupa :)")
                            }
                            Destinacio.PatternDetails.genericPath -> {
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
                            Destinacio.Projects.genericPath -> {
                                Icon(
                                    painterResource(id = R.drawable.home),
                                    contentDescription = "Read Icon")
                                Icon(
                                    painterResource(id = R.drawable.search),
                                    contentDescription = "Magnifying glass")
                            }
                            Destinacio.ProjectDetails.genericPath -> {
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
                            Destinacio.Profile.genericPath -> {
                                Icon(
                                    painterResource(id = R.drawable.share),
                                    contentDescription = "Share icon")
                            }
                            else -> {
                                bottomBarState.value = false
                        }
                    }
                }

            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {paddingValues ->
        RavelryDrawer(
            navigationController,
            coroutineScope,
            estatDrawer,
            modifier = Modifier.padding(paddingValues),
            rutaActual = currentPath,
            authManager = authManager,
            snackbarHostState)
    }
}


@Composable
fun RavelryDrawer(
    navigationController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    estatDrawer: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    modifier: Modifier,
    rutaActual: String,
    authManager: AuthManager,
    snackbarHostState: SnackbarHostState

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
                AsyncImage (
                    //Aqui ha d'anar la imatge d'usuari. tirarem d'un async de moment
                    model = AppUser.LoggedInUser.photo_url,
                    contentDescription = "User selected profile picture.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50.dp))
                        .weight(0.2F))
                Spacer (Modifier.height(10.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSecondaryContainer,modifier= Modifier.height(15.dp))
                Spacer (Modifier.height(10.dp))
                displaysWithDrawer.forEach(){
                    NavigationDrawerItem  (
                        label = { Text(it) },
                        selected = rutaActual.contains(it),
                        //icon = {Icon (imageVector = it.icon, it.title)},
                        onClick = {
                            coroutineScope.launch {
                                estatDrawer.close()
                            }
                            navigationController.navigate(it) {
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
                Text("Log out",
                    modifier = Modifier
                        .clickable { authManager.tancaSessio() }
                    )
            }
        },
        gesturesEnabled = rutaActual in displaysWithDrawer
    ) {
        NavigationGraph(navigationController, snackbarHostState)
    }
}