package com.ozioma.data.api

import com.ozioma.data.data.MarketPriceData
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of BitcoinInfoService which fetches its data from the server
 */
internal class RemoteBitcoinInfoService @Inject constructor(private val bitcoinInfoApi: BitcoinInfoApi) :
    BitcoinInfoService {
    override fun getCurrentBitcoinMarketPrice(): Single<MarketPriceData> {
        return bitcoinInfoApi.getBitcoinCurrentMarketPrice()
    }
}