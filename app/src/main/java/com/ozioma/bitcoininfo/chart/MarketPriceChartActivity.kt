package com.ozioma.bitcoininfo.chart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozioma.bitcoininfo.BitcoinDataApplication
import com.ozioma.bitcoininfo.R.*

class MarketPriceChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        BitcoinDataApplication.getAppComponent().inject(this)
    }
}