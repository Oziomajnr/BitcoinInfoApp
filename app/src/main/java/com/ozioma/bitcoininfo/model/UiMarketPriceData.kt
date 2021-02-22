package com.ozioma.bitcoininfo.model

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

data class UiMarketPriceData(
    val title: String,
    val period: String,
    val marketPrices: List<UiMarketPriceValue>
) {
    fun getLatestMarketPrice(): UiMarketPriceValue {
        return marketPrices.last()
    }

    fun toMpChartLineData(label: String): LineData {
        return LineData(LineDataSet(marketPrices.map { uiMarketPriceValues ->
            Entry(
                uiMarketPriceValues.timeStamp.toFloat(),
                uiMarketPriceValues.value.toFloat()
            )
        }, label))
    }
}