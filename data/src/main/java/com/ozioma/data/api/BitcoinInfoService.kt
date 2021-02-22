package com.ozioma.data.api

import com.ozioma.data.data.MarketPriceData
import io.reactivex.Single

interface BitcoinInfoService {
    fun getCurrentBitcoinMarketPrice(): Single<MarketPriceData>
}