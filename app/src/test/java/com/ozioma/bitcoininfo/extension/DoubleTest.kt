package com.ozioma.bitcoininfo.extension

import org.junit.Test

import org.junit.Assert.*

class DoubleTest {

    @Test
    fun `Formatting double to 2decimal rounds down correctly`() {
        assertEquals("412755.48", 412755.482745.formatTo2DecimalPlaces())
    }


    @Test
    fun `Formatting double to 2decimal rounds up correctly`() {
        assertEquals("55.49", 55.489745.formatTo2DecimalPlaces())
    }
}