package com.ozioma.bitcoininfo.chart

import org.junit.Test

import org.junit.Assert.*

class TimeStampToDateValueFormatterTest {

    @Test
    fun `getFormattedValue returns correct value`() {
        val timeStampToDateValueFormatter = TimeStampToDateValueFormatter()
        assertEquals(
            timeStampToDateValueFormatter.getFormattedValue(1613937132.toFloat()),
            "Feb 21"
        )
    }
}