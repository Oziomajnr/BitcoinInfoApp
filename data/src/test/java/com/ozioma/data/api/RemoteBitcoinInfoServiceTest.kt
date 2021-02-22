package com.ozioma.data.api

import com.ozioma.data.fakes.FakeBitcoinDataApi
import org.junit.Test

import com.ozioma.data.data.MarketPriceData
import com.ozioma.data.fakes.FakeMarketPriceDataFactory
import com.ozioma.data.fakes.FetchFailedException
import io.reactivex.observers.TestObserver
import org.junit.Before


class RemoteBitcoinInfoServiceTest {
    lateinit var testSubscriber: TestObserver<MarketPriceData>

    @Before
    fun setup() {
        testSubscriber = TestObserver()
    }

    @Test
    fun `getBitcoinMarketPrice relays correct data from BitcoinInfoApi`() {
        val fakeBitcoinInfoApi: BitcoinInfoApi =
            FakeBitcoinDataApi(FakeBitcoinDataApi.ResultType.SUCCESS)
        val bitcoinInfoService = RemoteBitcoinInfoService(fakeBitcoinInfoApi)
        val marketPriceDataSingle = bitcoinInfoService.getCurrentBitcoinMarketPrice()
        marketPriceDataSingle.subscribe(testSubscriber)
        testSubscriber.assertValue(FakeMarketPriceDataFactory.fakeMarketPriceData)
    }

    @Test
    fun `getBitcoinMarketPrice relays elrrors correctly from BitcoinInfoApi`() {
        val fakeBitcoinInfoApi: BitcoinInfoApi =
            FakeBitcoinDataApi(FakeBitcoinDataApi.ResultType.ERROR)
        val bitcoinInfoService = RemoteBitcoinInfoService(fakeBitcoinInfoApi)
        bitcoinInfoService.getCurrentBitcoinMarketPrice().subscribe(testSubscriber)
        testSubscriber.assertError(FetchFailedException::class.java)
    }
}