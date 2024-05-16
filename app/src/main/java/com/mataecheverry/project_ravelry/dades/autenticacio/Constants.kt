package com.mataecheverry.project_ravelry.dades.autenticacio

import com.mataecheverry.project_ravelry.dades.xarxa.api.RavelryClient


 const val CLIENT_ID = "4f5e0bb2a42f4e9adee9d4a67aaa6295"
 const val CLIENT_SECRET = "HMZfZ1Nf8PLnA0vjYLg/FaqFTd7U6VkC9N2pUJRc"
 const val GRANT_TYPE = "Authorization Code"
 const val CLIENT_AUTHENTICATION = "Send as Basic Auth header"
 const val CALLBACK = "com.mataecheverry.project_ravelry"
 const val URL_TOKEN = "https://www.ravelry.com/oauth2/token"
 const val URL_AUTH = "https://www.ravelry.com/oauth2/auth"

//Cal especificar el client_id i la uri cap a on redirigir a l'usuari.
//
 val URL_LOGIN_RAVELRY = "$URL_AUTH?client_id=$CLIENT_ID&scope=offline&redirect_uri=${RavelryClient.redirectURI}"



