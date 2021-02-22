package com.ozioma.data.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketPriceData(
   @Json(name = "status") val status: String,
   @Json(name = "name") val name: String,
   @Json(name = "unit") val unit: String,
   @Json(name = "period") val period: String,
   @Json(name = "description") val description: String,
   @Json(name = "values") val values: List<MarketPriceValues>
)