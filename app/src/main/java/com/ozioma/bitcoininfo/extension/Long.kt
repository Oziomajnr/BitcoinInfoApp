package com.ozioma.bitcoininfo.extension

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Converts epoch time stamp to formatted date string
 */
fun Long.formatTimestampToShortDate(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
        //multiply by 1000 because time stamp is in seconds
        // and simple date formatter expects time stamp in milli seconds
        return simpleDateFormat.format(this * 1000)
    } catch (e: Exception) {
        ""
    }
}