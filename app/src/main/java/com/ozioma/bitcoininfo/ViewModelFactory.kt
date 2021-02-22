package com.ozioma.bitcoininfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozioma.bitcoininfo.chart.MarketPriceChartViewModel
import com.ozioma.bitcoininfo.helper.DataHelper
import com.ozioma.data.api.BitcoinInfoService
import javax.inject.Inject

/**
 * Factory to get an new or existing ViewModel instance
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val bitcoinInfoService: BitcoinInfoService,
    val dataHelper: DataHelper
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MarketPriceChartViewModel::class.java) -> MarketPriceChartViewModel(
                bitcoinInfoService, dataHelper
            ) as T
            else -> error("Unknown ViewModel class $modelClass")
        }
    }
}