package com.ozioma.data.fakes

import com.ozioma.data.data.MarketPriceData
import com.ozioma.data.data.MarketPriceValues

internal class FakeMarketPriceDataFactory {
    companion object {
        private val fakeMarketPriceValues = listOf(MarketPriceValues(1611360000L, 33002.38))
        val fakeMarketPriceData = MarketPriceData(
            status = "ok", name = "Market Price",
            unit = "NGN", period = "1 year",
            description = "nothing", values = fakeMarketPriceValues
        )
    }
}