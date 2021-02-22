package com.ozioma.data.factory

import com.ozioma.data.BitcoinInfoServiceFactory
import org.junit.Test

import org.junit.Assert.*

class BitcoinInfoServiceFactoryTest {

    @Test
    fun `createInfoBitcoinService creates the service correctly`() {
        val bitcoinServiceFactory = BitcoinInfoServiceFactory()
        val factory = bitcoinServiceFactory.createBitcoinInfoService(false)
        assertNotNull("Bitcoin service factory is not created successfully", factory)
    }
}