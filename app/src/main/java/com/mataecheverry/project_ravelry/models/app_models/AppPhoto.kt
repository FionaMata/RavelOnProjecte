package com.mataecheverry.project_ravelry.models.app_models

data class AppPhoto(
   var caption: String = "",
   var caption_html: String = "",
   var copyright_holder: String ="",
   var id: Int = 0,
   var medium2_url: String = "",
   var medium_url: String = "",
   var small2_url: String = "",
   var small_url: String = "",
   var sort_order: String = "",
   var square_url: String= "",
   var thumbnail_url: String = "",
   var user_id: Int = 0,
   var x_offset: Int = 0,
   var y_offset: Int = 0,
   )