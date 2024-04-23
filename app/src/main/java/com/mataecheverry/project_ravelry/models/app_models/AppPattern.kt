package com.mataecheverry.project_ravelry.models.app_models

import android.os.Build
import androidx.annotation.RequiresApi
import com.mataecheverry.project_ravelry.models.api_models.FirstPhoto
import com.mataecheverry.project_ravelry.models.api_models.PatternSource
import com.mataecheverry.project_ravelry.models.api_models.User

//Recordar que nostre AppPattern és molt complet perquè la referència és Pattern (Full) a la documentació de ravelry)
data class AppPattern @RequiresApi(Build.VERSION_CODES.O) constructor(
    var id: Int = 0,
    var name: String = "",
    var craft: String = "",
    var difficulty_average: Float = 0F,
    var difficulty_count: Int = 0,
    var download_location: String = "",
    var downloadable: Boolean = false,
    var free: Boolean = false,
    var gauge: String = "",
    var gauge_description: String = "",
    var gauge_divisor: Int = 0,
    var gauge_patters: String = "",
    //@RequiresApi(Build.VERSION_CODES.O),
    //var generally_available = LocalDate.now() -- //Actualment omès.,
    var favorites_count: Int = 0,
    var projects_count: Int = 0,
    var queued_projects_count: Int = 0,
    var rating_count: Int = 0,
    var notes: String = "",
    //var packs: Pack -->  //Actualment omès	Packs connect a pattern to a suggested yarn. Within a pack, only "yarn_id" is required.
    //var patternAttribues: List<PatternAttribue> = listOf() -- //Actualment omès
    var pattern_author: AppPatternAuthor,
    var pattern_sources: List<PatternSource> = listOf(),
    //var pattern_categories: List<AppPatternCategories> = listOf()
    var pattern_needle_size: String = "",
    var pdf_in_library: Boolean = false,
    var pdf_url: String = "",
    var permalink: String = "",
    var photos: FirstPhoto,
    var price: String = "",
    //var printings: List<Printing> = listOf(),
    var product_id: Int = 0,
    var rating_average: Float = 0F,
    var ravelry_download: Boolean = false,
    var row_gauge: String = "",
    var sizes_available: String = "",
    var unlisted_product_ids: List<Int> = emptyList(),
    var url: String = "",
    var volumes_in_library: List<Int> = listOf(),
    var yardage: Int = 0,
    var yardage_description: String = "",
    var yardage_max: Int = 0,
    //yarn_list_type,
    //yarn_weight 	YarnWeight (list),
    var yarn_weight_description: String = "",
    val users: List<User>
)

