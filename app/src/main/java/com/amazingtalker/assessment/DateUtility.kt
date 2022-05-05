package com.amazingtalker.assessment

import java.text.SimpleDateFormat
import java.util.*

object DateUtility {

    private fun getDayByFormat(offset: Int = 0, dateFormat: SimpleDateFormat): String {
        return if (offset == 0) {
            dateFormat.format(Date())
        } else {
            val c = Calendar.getInstance()
            c.time = Date()
            c.add(Calendar.DATE, offset)
            dateFormat.format(c.time)
        }
    }
    // Title Template
    fun getDateString(offset: Int = 0): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return getDayByFormat(offset, dateFormat)
    }

    // Subtitle Template
    fun getSubtitleDate(offset: Int = 0): String {
        val dateFormat = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())
        return getDayByFormat(offset, dateFormat)
    }
}