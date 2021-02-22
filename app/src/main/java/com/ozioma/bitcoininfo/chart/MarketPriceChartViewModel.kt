package com.ozioma.bitcoininfo.chart

import androidx.lifecycle.LiveData
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
    private val _marketPriceLiveData: MutableLiveData<UiResult<UiMarketPriceData>> =
        MutableLiveData()
    val marketPriceLiveData: LiveData<UiResult<UiMarketPriceData>> = _marketPriceLiveData

    init {
        getMarketPriceData()
    }

    fun getMarketPriceData() {
        _marketPriceLiveData.value = UiResult.Loading()
        disposable += bitcoinInfoService.getCurrentBitcoinMarketPrice().map {
            dataHelper.marketPriceDataToUIMarketPriceData(it)
        }.applyDefaultSchedulers()
            .subscribeBy(
                onSuccess = {
                    _marketPriceLiveData.value =
                        UiResult.Success(it)
                },
                onError = {
                    _marketPriceLiveData.value =
                        UiResult.Error()
                })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}