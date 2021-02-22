package com.ozioma.bitcoininfo.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import com.ozioma.bitcoininfo.extension.formatTimestampToShortDate

class TimeStampToDateValueFormatter : ValueFormatter() {
    /**
     * Return chart x value from epoch time stamp to formatted date string
     */
    override fun getFormattedValue(value: Float): String {
        return value.toLong().formatTimestampToShortDate()
    }
}