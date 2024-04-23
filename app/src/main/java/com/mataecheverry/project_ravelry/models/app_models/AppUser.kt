package com.mataecheverry.project_ravelry.models.app_models

import com.google.firebase.auth.AuthCredential

open class AppUser() {

    var id: Int = 0
    var about_me_html: String = ""
    var fave_colors : String = ""
    var fave_curse: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var large_photo_url= ""
    var location: String= ""
    var pattern_author: String= ""
    var photo_url: String= ""
    var small_photo_url: String= ""
    var tiny_photo_url: String= ""
    //var user_sites: UserSite= ""
    var username: String= ""
    var email: String = ""

    //part per singleton
    var user_password: String = ""
    lateinit var user_credentials: AuthCredential
    var user_token: String = ""
}

class LoggedInUser : AppUser {
    constructor(
        user_mail: String = "",
        user_password: String = "",
        credentials: AuthCredential,
        user_token: String = ""
    ) : super()

    companion object {
        var user_mail: String = ""
        var user_password: String = ""
        lateinit var credentials: AuthCredential
        var user_token: String = ""
    }
}
