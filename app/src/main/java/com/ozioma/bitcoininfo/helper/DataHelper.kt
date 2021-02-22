package com.ozioma.bitcoininfo.helper

import com.ozioma.bitcoininfo.model.UiMarketPriceData
import com.ozioma.bitcoininfo.model.UiMarketPriceValue
import com.ozioma.data.data.MarketPriceData
import javax.inject.Inject

/**
 * Utility class to help with simple data manipulation on the UI layer
 */
class DataHelper @Inject constructor() {
    fun marketPriceDataToUIMarketPriceData(marketPriceData: MarketPriceData): UiMarketPriceData {
        return UiMarketPriceData(
            marketPriceData.name,
            marketPriceData.period,
            marketPriceData.values.map {
                UiMarketPriceValue(it.timeStamp, it.value)
            })
    }
}