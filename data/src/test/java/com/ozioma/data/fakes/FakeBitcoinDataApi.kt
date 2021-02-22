package com.ozioma.data.fakes

import com.ozioma.data.api.BitcoinInfoApi
import com.ozioma.data.data.MarketPriceData
import io.reactivex.Single
import java.lang.Exception

class FakeBitcoinDataApi(private val resultType: ResultType) : BitcoinInfoApi {
    override fun getBitcoinCurrentMarketPrice(
        timeSpan: String,
        format: String
    ): Single<MarketPriceData> {
        return when (resultType) {
            ResultType.ERROR -> Single.error(FetchFailedException("An error occurred while fetching the bitcoininfoapp.data"))
            ResultType.SUCCESS -> Single.just(FakeMarketPriceDataFactory.fakeMarketPriceData)
        }
    }

    /**
     * The type of result that getBitcoinCurrentMarketPrice() would return
     */
    enum class ResultType {
        ERROR,
        SUCCESS
    }
}

class FetchFailedException(message: String) : Exception(message)