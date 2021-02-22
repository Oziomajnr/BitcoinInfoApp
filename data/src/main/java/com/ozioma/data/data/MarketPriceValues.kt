package com.ozioma.data.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketPriceValues(
    @Json(name = "x") val timeStamp: Long,
    @Json(name = "y") val value: Double
)