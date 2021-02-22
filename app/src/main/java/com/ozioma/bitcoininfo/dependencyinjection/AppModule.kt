package com.ozioma.bitcoininfo.dependencyinjection

import com.ozioma.bitcoininfo.BuildConfig
import com.ozioma.bitcoininfo.ViewModelFactory
import com.ozioma.bitcoininfo.helper.DataHelper
import com.ozioma.data.BitcoinInfoServiceFactory
import com.ozioma.data.api.BitcoinInfoService
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    @PerApplication
    fun provideBitcoinInfoServiceFactory() = BitcoinInfoServiceFactory()

    @Provides
    @PerApplication
    fun provideBitcoinInfoService(bitcoinInfoServiceFactory: BitcoinInfoServiceFactory) =
        bitcoinInfoServiceFactory.createBitcoinInfoService(BuildConfig.DEBUG)

    @Provides
    @PerApplication
    fun provideViewModelFactory(bitcoinInfoService: BitcoinInfoService, dataHelper: DataHelper) =
        ViewModelFactory(bitcoinInfoService, dataHelper)
}