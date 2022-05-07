package com.amazingtalker.assessment

import com.amazingtalker.assessment.data.CourseUtilities
import com.amazingtalker.assessment.data.Courses
import com.amazingtalker.assessment.data.DateUtility
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun date_utility() {
        System.out.println("getDayFromToday: " + DateUtility.getSubtitleDate(0))
    }

    @Test
    fun response_string_to_Courses() {
        val generalInfoJson =
            "{'available': [{'start': '2022-05-07T00:30:00Z', 'end': '2022-05-07T03:00:00Z' }, {'start': '2022-05-07T04:00:00Z','end': '2022-05-07T08:00:00Z' }], 'booked': [{'start': '2022-05-07T03:00:00Z', 'end': '2022-05-07T04:00:00Z' }]}"
        val gson = Gson()
        val coursesObject: Courses = gson.fromJson(generalInfoJson, Courses::class.java)
        assertEquals("2022-05-07T00:30:00Z", coursesObject.available?.get(0)?.start)
    }

    @Test
    fun merge_timeline_from_Courses() {
        val generalInfoJson =
            "{'available': [{'start': '2022-05-07T00:30:00Z', 'end': '2022-05-07T03:00:00Z' }, {'start': '2022-05-07T04:00:00Z','end': '2022-05-07T08:00:00Z' }], 'booked': [{'start': '2021-05-07T03:00:00Z', 'end': '2022-05-07T04:00:00Z' }]}"
        val gson = Gson()
        val coursesObject: Courses = gson.fromJson(generalInfoJson, Courses::class.java)

        System.out.println("mergedList: " + CourseUtilities.handle(coursesObject).joinToString(" "))
    }

    @Test
    fun timezone_verify() {
        val dateStr = "2022-05-07T00:30:00Z"
        val formattedDate = DateUtility.transformToLocalTime(dateStr, TimeZone.getTimeZone("Asia/Taipei"))
        assertEquals(formattedDate.toString(), "2022-05-07T08:30:00Z")
    }
}