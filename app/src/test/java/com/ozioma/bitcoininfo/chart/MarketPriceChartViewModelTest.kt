package com.ozioma.bitcoininfo.chart

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ozioma.bitcoininfo.UiResult
import com.ozioma.bitcoininfo.helper.DataHelper
import com.ozioma.bitcoininfo.model.UiMarketPriceData
import com.ozioma.bitcoininfo.model.UiMarketPriceValue
import com.ozioma.bitcoininfo.testingutil.getOrAwaitValue
import com.ozioma.data.api.BitcoinInfoService
import com.ozioma.data.data.MarketPriceData
import com.ozioma.data.data.MarketPriceValues
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class MarketPriceChartViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    val fakeMarketPriceData = MarketPriceData(
        status = "ok",
        name = "Market Price",
        unit = "NGN",
        period = "1 year",
        description = "nothing",
        values = listOf(MarketPriceValues(1611360000L, 33002.38))
    )

    private val fakeUiMarketPriceData = UiMarketPriceData(
        "Market Price",
        "1 year",
        listOf(UiMarketPriceValue(1611360000L, 33002.38), UiMarketPriceValue(161136L, 33.38))
    )

    private val fakeBitcoinInfoService = object : BitcoinInfoService {
        override fun getCurrentBitcoinMarketPrice(): Single<MarketPriceData> {
            return Single.just(
                fakeMarketPriceData
            )
        }
    }

    private var dataHelper = Mockito.mock(DataHelper::class.java)

    @Before
    fun setup() {
        Mockito.`when`(dataHelper.marketPriceDataToUIMarketPriceData(fakeMarketPriceData))
            .thenReturn(fakeUiMarketPriceData)
    }

    @Test
    fun `market price view model fetches market price data as soon as its created `() {

        val dataHelper = DataHelper()

        val marketPriceDataViewModel =
            MarketPriceChartViewModel(fakeBitcoinInfoService, dataHelper)
        assertNotNull(marketPriceDataViewModel.marketPriceLiveData.getOrAwaitValue())
    }

    @Test
    fun `currentMarketPriceLiveData Enters loading state correctly `() {

        val dataHelper = DataHelper()

        val marketPriceDataViewModel =
            MarketPriceChartViewModel(fakeBitcoinInfoService, dataHelper)
        assertTrue(marketPriceDataViewModel.marketPriceLiveData.getOrAwaitValue() is UiResult.Loading)
    }
}