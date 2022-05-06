package com.amazingtalker.assessment

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
/*
    E.g: 2022-05-04T20:17:46.384Z
    - The T doesn’t really stand for anything. It is just the separator that the ISO 8601 combined date-time format requires.
    You can read it as an abbreviation for Time.
    - The Z stands for the Zero timezone, as it is offset by 0 from the Coordinated Universal Time (UTC).
 */
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

    fun getSevenString(offset: Int = 0): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return getDayByFormat(offset, dateFormat) + " ~ " + getDayByFormat(offset + 6, dateFormat)
    }

    // Subtitle Template
    fun getSubtitleDate(offset: Int = 0): String {
        val dateFormat = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())

        return getDayByFormat(offset, dateFormat)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun tempCodes() {
        val numbers1 = setOf("2022-05-07T00:30:00Z", "2022-05-08T00:30:00Z", "2022-05-08T10:30:00Z")
        val numbers2 = setOf("2022-05-07T10:00:00Z", "2022-05-07T15:30:00Z", "2022-05-08T10:30:00Z")

        val text = "2022-05-07T00:30:00Z"
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(text)
        //val date = SimpleDateFormat("dd-MM-yyyy").parse("14-02-2018")

        println(date.time)
        //  @RequiresApi(Build.VERSION_CODES.O)
        val now = Instant.now()

        // @RequiresApi(Build.VERSION_CODES.O)
        val zdt = ZonedDateTime.ofInstant(now, ZoneId.systemDefault())
        val currentDateTime = Date()

        val localDate = now.atZone(ZoneId.of("+8"))
        println(currentDateTime)
        println(localDate)
        println(zdt)
    }
}