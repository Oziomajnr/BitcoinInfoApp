package com.ozioma.bitcoininfo.extension

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Formats a double to a string rounded off to 2 decimal places
 */
fun Double.formatTo2DecimalPlaces(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.HALF_UP
    return df.format(this)
}
