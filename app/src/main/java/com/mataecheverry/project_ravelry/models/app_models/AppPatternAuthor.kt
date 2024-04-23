package com.mataecheverry.project_ravelry.models.app_models

import com.mataecheverry.project_ravelry.models.api_models.User

class AppPatternAuthor(id: Int, name: String, users: List<User>) {
    var id: Int = 0
    var crochet_pattern_count: String = ""
    var favorites_count : String = ""
    var knitting_pattern_count: String = ""
    var name: String = ""
    var notes= ""
    var notes_html: String= ""
    var patterns_count: String= ""
    var permalink: String= ""
    var users: List<User> = listOf()
}