package com.mataecheverry.project_ravelry.dades.autenticacio

import com.google.firebase.auth.AuthCredential

const val URL_AUTH ="https://www.ravelry.com/oauth2/auth"
 const val URL_TOKEN = "https://www.ravelry.com/oauth2/token"
 const val URL_API = "https://api.ravelry.com/"
 const val CLIENT_ID = "4f5e0bb2a42f4e9adee9d4a67aaa6295"
 const val CLIENT_SECRET = "HMZfZ1Nf8PLnA0vjYLg/FaqFTd7U6VkC9N2pUJRc"
 const val GRANT_TYPE = "Authorization Code"
 const val CLIENT_AUTHENTICATION = "Send as Basic Auth header"
 const val CALLBACK = "com.mataecheverry.project_ravelry"


//NO CONSTANTS
//Per credencials fer singleton per poder utilitzar

object LoggedInUser {
 var userMail: String =""
 var userPassword: String =""
 lateinit var credentials: AuthCredential
 var userToken: String =""

 fun initialize(userMail: String, userPassword: String) {
  this.userMail = userMail
  this.userPassword = userPassword
 }

  fun initialize (credentials: AuthCredential){
   this.credentials = credentials
  }
 }
