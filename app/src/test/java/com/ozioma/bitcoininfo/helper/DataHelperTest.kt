package com.ozioma.bitcoininfo.helper

import com.ozioma.bitcoininfo.model.UiMarketPriceData
import com.ozioma.bitcoininfo.model.UiMarketPriceValue
import com.ozioma.data.data.MarketPriceData
import com.ozioma.data.data.MarketPriceValues
import org.junit.Assert
import org.junit.Test

class DataHelperTest {

    @Test
    fun `marketPriceDataToUIMarketPriceData conversion works correctly`() {
        val dataHelper = DataHelper()
        val marketPriceData = MarketPriceData(
            status = "ok", name = "Market Price",
            unit = "NGN", period = "1 year",
            description = "nothing", values = listOf(MarketPriceValues(1611360000L, 33002.38))
        )
        val uiMarketPrice = dataHelper.marketPriceDataToUIMarketPriceData(marketPriceData)
        val expectedUiMarketPrice = UiMarketPriceData(
            marketPriceData.name,
            marketPriceData.period,
            marketPriceData.values.map {
                UiMarketPriceValue(it.timeStamp, it.value)
            })
        Assert.assertEquals(expectedUiMarketPrice.toString(), uiMarketPrice.toString())
    }

}