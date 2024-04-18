package com.mataecheverry.project_ravelry.dades.api_models


import com.google.gson.annotations.SerializedName

data class PatternSource(
    @SerializedName("amazon_rating")
    val amazonRating: Any,
    @SerializedName("amazon_reviews")
    val amazonReviews: Any,
    @SerializedName("amazon_sales_rank")
    val amazonSalesRank: Any,
    @SerializedName("amazon_updated_at")
    val amazonUpdatedAt: Any,
    @SerializedName("amazon_url")
    val amazonUrl: Any,
    @SerializedName("approved_patterns_count")
    val approvedPatternsCount: Int,
    @SerializedName("asin")
    val asin: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("author_pattern_author_id")
    val authorPatternAuthorId: Int,
    @SerializedName("author_surname")
    val authorSurname: String,
    @SerializedName("book_binding")
    val bookBinding: Any,
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by_user_id")
    val createdByUserId: Int,
    @SerializedName("designer_pending_patterns_count")
    val designerPendingPatternsCount: Int,
    @SerializedName("designer_users_count")
    val designerUsersCount: Int,
    @SerializedName("editorships_count")
    val editorshipsCount: Int,
    @SerializedName("favorites_count")
    val favoritesCount: Int,
    @SerializedName("first_photo_id")
    val firstPhotoId: Int,
    @SerializedName("flaggings_count")
    val flaggingsCount: Int,
    @SerializedName("fulfilled_by_ravelry")
    val fulfilledByRavelry: Boolean,
    @SerializedName("has_photo")
    val hasPhoto: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isbn_13")
    val isbn13: String,
    @SerializedName("issue")
    val issue: String,
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("label")
    val label: Any,
    @SerializedName("large_image_url")
    val largeImageUrl: Any,
    @SerializedName("last_pattern_edit")
    val lastPatternEdit: String,
    @SerializedName("link_id")
    val linkId: Int,
    @SerializedName("list_price")
    val listPrice: Any,
    @SerializedName("lock_version")
    val lockVersion: Int,
    @SerializedName("medium_image_url")
    val mediumImageUrl: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("out_of_print")
    val outOfPrint: Boolean,
    @SerializedName("pattern_source_type_id")
    val patternSourceTypeId: Int,
    @SerializedName("patterns_count")
    val patternsCount: Int,
    @SerializedName("pending_patterns_count")
    val pendingPatternsCount: Int,
    @SerializedName("periodical")
    val periodical: Boolean,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("photos_permitted")
    val photosPermitted: Boolean,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("popularity_rank")
    val popularityRank: Int,
    @SerializedName("price")
    val price: Any,
    @SerializedName("publication_date")
    val publicationDate: String,
    @SerializedName("publication_date_set")
    val publicationDateSet: Int,
    @SerializedName("publication_day_set")
    val publicationDaySet: Int,
    @SerializedName("publication_sort_order")
    val publicationSortOrder: Int,
    @SerializedName("publication_year")
    val publicationYear: Int,
    @SerializedName("publisher_id")
    val publisherId: Int,
    @SerializedName("shelf_image_path")
    val shelfImagePath: String,
    @SerializedName("shelf_image_size")
    val shelfImageSize: String,
    @SerializedName("small_image_url")
    val smallImageUrl: String,
    @SerializedName("source_group_id")
    val sourceGroupId: Int,
    @SerializedName("stickies_count")
    val stickiesCount: Int,
    @SerializedName("store_id")
    val storeId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("work_id")
    val workId: Any
)