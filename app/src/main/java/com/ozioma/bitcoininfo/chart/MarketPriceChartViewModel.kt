package com.ozioma.bitcoininfo.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozioma.bitcoininfo.UiResult
import com.ozioma.bitcoininfo.extension.RxJavaExtension.applyDefaultSchedulers
import com.ozioma.bitcoininfo.model.UiMarketPriceData
import com.ozioma.bitcoininfo.helper.DataHelper
import com.ozioma.data.api.BitcoinInfoService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class MarketPriceChartViewModel(
    private val bitcoinInfoService: BitcoinInfoService,
    private val dataHelper: DataHelper
) : ViewModel() {
    private val disposable = CompositeDisposable()
    val marketPriceLiveData: MutableLiveData<UiResult<UiMarketPriceData>> = MutableLiveData()

    init {
        getMarketPriceData()
    }

    fun getMarketPriceData() {
        marketPriceLiveData.value = UiResult.Loading("Fetching market prices from server")
        disposable += bitcoinInfoService.getCurrentBitcoinMarketPrice().map {
            dataHelper.marketPriceDataToUIMarketPriceData(it)
        }.applyDefaultSchedulers()
            .subscribeBy(
                onSuccess = {
                    marketPriceLiveData.value =
                        UiResult.Success(it, "Market prices fetched successfully")
                },
                onError = {
                    marketPriceLiveData.value =
                        UiResult.Error("An error occurred while loading market prices")
                })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}