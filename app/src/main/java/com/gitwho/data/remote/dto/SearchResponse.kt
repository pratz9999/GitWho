package com.gitwho.data.remote.dto

import com.squareup.moshi.Json

data class SearchResponse(

    @Json(name = "total_count")
    val totalCount: Int? = null,

    @Json(name = "incomplete_results")
    val incompleteResults: Boolean? = null,

    @Json(name = "items")
    val items: List<Item>? = null
)