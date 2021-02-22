package com.ozioma.bitcoininfo.dependencyinjection

import com.ozioma.bitcoininfo.BitcoinDataApplication
import com.ozioma.bitcoininfo.ViewModelFactory
import com.ozioma.bitcoininfo.chart.MarketPriceChartActivity
import com.ozioma.bitcoininfo.chart.MarketPriceChartViewModel
import com.ozioma.bitcoininfo.chart.MarketPriceFragment
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(npoApplication: BitcoinDataApplication)

    fun inject(marketPriceChartActivity: MarketPriceChartActivity)

    fun inject(marketPriceFragment: MarketPriceFragment)

    fun inject(marketPriceChartViewModel: MarketPriceChartViewModel)

    fun getViewModelFactory(): ViewModelFactory
}