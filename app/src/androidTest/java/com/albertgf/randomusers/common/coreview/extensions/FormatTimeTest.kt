package com.albertgf.randomusers.common.coreview.extensions

import org.junit.Assert
import org.junit.Test

class FormatTimeTest {

    @Test
    fun validate_formatter() {
        val date = "1957-07-20T22:07:55.848Z".formatTime()

        Assert.assertTrue(date == "20/07/1957")
    }
}