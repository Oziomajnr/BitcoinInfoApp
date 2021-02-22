package com.ozioma.bitcoininfo.extension

import org.junit.Test

import org.junit.Assert.*

class LongKtTest {

    @Test
    fun `timeStampToFormattedDateString works correctly`() {
        assertEquals("Feb 19", 1613692800L.formatTimestampToShortDate())
    }
}