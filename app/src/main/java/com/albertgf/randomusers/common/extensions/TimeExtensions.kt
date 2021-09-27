package com.albertgf.randomusers.common.extensions

import com.albertgf.randomusers.common.extensions.TimeFormat.Companion.DATEFORMAT_ISO
import com.albertgf.randomusers.common.extensions.TimeFormat.Companion.DATEFORMAT_VIEW
import com.albertgf.randomusers.common.extensions.TimeFormat.Companion.TIMEZONE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeFormat {
    companion object {
        const val DATEFORMAT_VIEW = "dd/MM/yyyy"
        const val DATEFORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val TIMEZONE = "UTC"
    }
}

fun String.formatTime(from: String = DATEFORMAT_ISO, to: String = DATEFORMAT_VIEW): String {
    var timeFormatted = ""
    val tz = TimeZone.getTimeZone(TIMEZONE)
    val dfTo = SimpleDateFormat(to, Locale.US)
    dfTo.timeZone = tz
    val dfFrom = SimpleDateFormat(from, Locale.US)
    dfFrom.timeZone = tz
    try {
        val timeMillis = dfFrom.parse(this)?.time
        timeFormatted = dfTo.format(timeMillis)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return timeFormatted
}