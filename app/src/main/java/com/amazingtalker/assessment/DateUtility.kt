package com.amazingtalker.assessment

import java.text.SimpleDateFormat
import java.util.*

object DateUtility {

    fun getDateString(offset: Int = 0): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return if (offset == 0) {
            dateFormat.format(Date())
        } else {
            val c = Calendar.getInstance()
            c.time = Date()
            c.add(Calendar.DATE, offset)
            dateFormat.format(c.time)
        }
    }
}