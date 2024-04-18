package com.mataecheverry.project_ravelry.dades.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mataecheverry.project_ravelry.R


enum class NavigationCat(
    val previousPath: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
){
    Login ("Login", R.drawable.user, R.string.login),
    Register("Registre", R.drawable.user, R.string.register),
    Recover("Recover", R.drawable.typeselectedstateenabled, R.string.recover),
    Home("Home", R.drawable.home, R.string.home),
    Search("Search", R.drawable.search, R.string.search),
    Patterns("Patterns", R.drawable.fakeyarn, R.string.patterns),
    Favorites("Favorites", R.drawable.heart, R.string.favorites),
    Projects("Projects", R.drawable.projects, R.string.projects),
    Shops("Shops", R.drawable.shop, R.string.shop),
    UploadProject("UploadProject", R.drawable.plus, R.string.uploadproject),
    Calendar("Calendar", R.drawable.calendar, R.string.calendar),
    Profile("Profile", R.drawable.user, R.string.profile),
    About("About", R.drawable.about, R.string.about)
}


sealed class Destinacio(
    val basePath: String,
    val navArguments: List<NavArguments> = emptyList()
){

    val navArgs = navArguments.map { it.toNavArgument() }
    val genericPath = run {
        val argumentKeys = navArguments.map {"{${it.key}}"}
        listOf(basePath)
            .plus(argumentKeys)
            .joinToString("/")
    }

    object Login: Destinacio(NavigationCat.Login.previousPath+"/Start")
    object Register: Destinacio(NavigationCat.Login.previousPath+"/Register")
    object Recover: Destinacio(NavigationCat.Login.previousPath+"/Recover")
    object Home: Destinacio(NavigationCat.Home.previousPath+"/Start")
    object Search: Destinacio(NavigationCat.Search.previousPath+"/Start")
    object Patterns: Destinacio(NavigationCat.Patterns.previousPath+"/Start")
    object PatternDetails: Destinacio(NavigationCat.Patterns.previousPath+"/Patterns",
            listOf(NavArguments.IdDetail)){
        fun CreatePathWithArguments(IdDetail: String) = "$basePath/$IdDetail"
    }
    object Favorites: Destinacio(NavigationCat.Favorites.previousPath+"/Start")
    object FavoriteDetails: Destinacio(NavigationCat.Favorites.previousPath+"/Favorites")
    object Projects: Destinacio(NavigationCat.Favorites.previousPath+"/Start")
    object ProjectDetails: Destinacio(NavigationCat.Projects.previousPath+"/Projects",
        listOf(NavArguments.IdDetail)){
        fun CreatePathWithArguments(IdDetail: String) = "$basePath/$IdDetail"
    }

    object Shops: Destinacio(NavigationCat.Shops.previousPath+"/Start")
    object ShopDetails: Destinacio(NavigationCat.Shops.previousPath+"/Shops",
        listOf(NavArguments.IdDetail)){
        fun CreatePathWithArguments(IdDetail: String) = "$basePath/$IdDetail"
    }

    object Calendar: Destinacio(NavigationCat.Calendar.previousPath+"/Start")
    object CalendarDetails: Destinacio(NavigationCat.Calendar.previousPath+"/Calendar",
        listOf(NavArguments.IdDetail)){
        fun CreatePathWithArguments(IdDetail: String) = "$basePath/$IdDetail"
    }
    object UploadProject: Destinacio(NavigationCat.UploadProject.previousPath+"/Start")
    object About: Destinacio(NavigationCat.About.previousPath+"/Start")
    object Profile: Destinacio(NavigationCat.Profile.previousPath+"/Profile")

}

enum class NavArguments(val key: String, var tipus: NavType<*>){
    IdDetail("IdDetail", NavType.StringType);

    fun toNavArgument(): NamedNavArgument{
        return navArgument(key) {type = tipus}
    }

}
