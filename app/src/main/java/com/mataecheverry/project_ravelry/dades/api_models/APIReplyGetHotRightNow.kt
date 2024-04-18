package com.mataecheverry.project_ravelry.dades.api_models


import com.google.gson.annotations.SerializedName
import com.mataecheverry.project_ravelry.dades.app_models.AppPattern

/**Posem nom genèric APIReplyGetPatterns pq farem com a mínim tres consultes que
 * tornen la combinació Paginator-List<Pattern>: HotRightNow, DebutPatterns i
 * Highlights. */

data class APIReplyGetPatterns(
    @SerializedName("paginator")
    val paginator: Paginator,
    @SerializedName("patterns")
    val patterns: List<Pattern>

)

fun Pattern.toAppPattern(): AppPattern {
    return AppPattern(
        id = this.id,
        name = this.name,
        free = this.free,
        patternAuthor = this.patternAuthor.toAppPatternAuthor(),
        users = this.patternAuthor.users
    )
}