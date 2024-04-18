package com.mataecheverry.project_ravelry.dades.app_models

class AppProject () {
    var user: AppUser = AppUser()
    var id: Int = 0
    var patternName: String = ""
    var first_photo: String = ""

    //S'ampliarà a mesura de lo necessari.
    //La classe importada a través de la API convertirà cap a aquesta.
}