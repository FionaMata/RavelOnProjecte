package com.mataecheverry.project_ravelry.models.api_models


import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import com.mataecheverry.project_ravelry.models.app_models.AppPattern

/**Posem nom genèric APIReplyGetPatterns pq farem com a mínim tres consultes que
 * tornen la combinació Paginator-List<Pattern>: HotRightNow, DebutPatterns i
 * Highlights. */

data class APIReplyGetPatterns(
    @SerializedName("paginator")
    val paginator: Paginator,
    @SerializedName("patterns")
    val patterns: List<Pattern>
)

@RequiresApi(Build.VERSION_CODES.O)
fun APIReplyGetPatterns.ToListOfAppPatterns(): List<AppPattern>{
    return patterns.map {
        AppPattern(
            id = it.id,
            name = it.name,
            free = it.free,
            users = it.designer.users,
            pattern_author = it.patternAuthor.toAppPatternAuthor(),
            photos = it.firstPhoto
        )
    }
}

