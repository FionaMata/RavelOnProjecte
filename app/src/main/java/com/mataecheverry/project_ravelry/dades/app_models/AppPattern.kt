package com.mataecheverry.project_ravelry.dades.app_models

import android.os.Build
import androidx.annotation.RequiresApi
import com.mataecheverry.project_ravelry.dades.api_models.User
import java.time.LocalDate

//Recordar que nostre AppPattern és molt complet perquè la referència és Pattern (Full) a la documentació de ravelry)
class AppPattern(id: Int, name: String, free: Boolean, patternAuthor: AppPatternAuthor, users: List<User>) {
    var id: Int = 0
    var name = ""
    var craft: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    var created_at: LocalDate = LocalDate.now()
    var difficulty_average: Float = 0F
    var difficulty_count: Int = 0
    var download_location: String = ""
    var downloadable: Boolean = false
    var free: Boolean = false
    var gauge: String = ""
    var gauge_description = ""
    var gauge_divisor = 0
    var gauge_patters = ""
    //@RequiresApi(Build.VERSION_CODES.O)
    //var generally_available = LocalDate.now() -- //Actualment omès.
    var favorites_count = 0
    var projects_count = 0
    var queued_projects_count = 0
    var rating_count = 0
    var notes = ""
    //var packs: Pack -->  //Actualment omès	Packs connect a pattern to a suggested yarn. Within a pack, only "yarn_id" is required.
    //var patternAttribues: List<PatternAttribue> = listOf() -- //Actualment omès
    var pattern_author: AppPatternAuthor = AppPatternAuthor(
        id = id,
        name = name,
        users = users
    )
    //var pattern_categories: List<AppPatternCategories> = listOf()
    var pattern_needle_size = ""
    var pdf_in_library:	Boolean = false
    var pdf_url = ""
    var permalink = ""
    var personal_attributes: HashMap<String, Any> = HashMap()
    //var photos: List<Photo> = emptyList()
    var price = ""
    //var printings: List<Printing> = listOf()
    var product_id =0
    @RequiresApi(Build.VERSION_CODES.O)
    var published = LocalDate.now()
    var rating_average = 0F
    var ravelry_download = false
    var row_gauge = ""
    var sizes_available = ""
    var unlisted_product_ids = listOf<Int>()
    @RequiresApi(Build.VERSION_CODES.O)
    var updated_at = LocalDate.now()
    var url= ""
    var volumes_in_library= listOf<Int>()
    var yardage = 0
    var yardage_description = ""
    var yardage_max = 0
    //yarn_list_type
    //yarn_weight 	YarnWeight (list)
    var yarn_weight_description = ""
}

