package com.mataecheverry.project_ravelry.models.app_models

open class AppUser() {

    var id: Int = 0  //x
    var about_me_html: String = ""
    var fave_colors : String = ""
    var fave_curse: String = ""
    var first_name: String = ""  //x
    var last_name: String = ""  //x
    var large_photo_url= ""
    var location: String= ""
    var pattern_author: String= ""
    var photo_url: String= ""
    var small_photo_url: String= ""
    var tiny_photo_url: String= ""
    //var user_sites: UserSite= ""
    var username: String= ""
    var email: String = ""  //x

    //part per singleton
    var user_password: String = ""
    //lateinit var user_credentials: AuthCredential
    var user_token: String = ""


    object LoggedInUser: AppUser()
}


