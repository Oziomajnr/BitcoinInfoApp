package com.ozioma.bitcoininfo.model

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UiMarketPriceDataTest {

    @Test
    fun `getLatestMarketPrice returns correct value`() {
        val uiMarketPriceData = UiMarketPriceData(
            "Market Price",
            "1 year",
            listOf(UiMarketPriceValue(1611360000L, 33002.38), UiMarketPriceValue(161136L, 33.38))
        )
        assertEquals(UiMarketPriceValue(161136L, 33.38), uiMarketPriceData.getLatestMarketPrice())
    }

    @Test
    fun toMpChartLineData() {
        val uiMarketPriceData = UiMarketPriceData(
            "Market Price",
            "1 year",
            listOf(UiMarketPriceValue(1611360000L, 33002.38), UiMarketPriceValue(161136L, 33.38))
        )
        val label = "Market Price"
        val expectedLineData = LineData(
            LineDataSet(
                listOf(
                    Entry(
                        1611360000L.toFloat(),
                        33002.38F
                    )
                ), label
            )
        )
        val actualLineData = uiMarketPriceData.toMpChartLineData(label)
        assertTrue(lineDataAreEqual(expectedLineData, actualLineData))
    }

    private fun lineDataAreEqual(lineData1: LineData, lineData2: LineData): Boolean {
        var hasMismatchedItems = false
        val datasetEntryCount = lineData1.dataSets.first().entryCount
        for (index in 0 until datasetEntryCount) {
            if (lineData1.dataSets.first().getEntryForIndex(index).x != lineData2.dataSets.first()
                    .getEntryForIndex(index).x || lineData1.dataSets.first()
                    .getEntryForIndex(index).y != lineData2.dataSets.first()
                    .getEntryForIndex(index).y
            ) {
                hasMismatchedItems = true
            }
        }
        return lineData1.dataSetLabels.contentDeepEquals(lineData2.dataSetLabels) && !hasMismatchedItems
    }
}