package com.ozioma.data.api

import com.ozioma.data.data.MarketPriceData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BitcoinInfoApi {
    @GET("market-price")
    fun getBitcoinCurrentMarketPrice(
        @Query("timespan") timeSpan: String = DEFAULT_TIME_SPAN,
        @Query("format") format: String = DEFAULT_FORMAT,
    ): Single<MarketPriceData>

    companion object {
        const val DEFAULT_TIME_SPAN = "7days"
        const val DEFAULT_FORMAT = "json"
    }
}