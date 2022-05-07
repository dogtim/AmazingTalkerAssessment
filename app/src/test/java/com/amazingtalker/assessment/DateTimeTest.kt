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
class DateTimeTest {

    @Test
    fun response_string_to_Courses() {
        val data =
            "{'available': [{'start': '2022-05-07T00:30:00Z', 'end': '2022-05-07T03:00:00Z' }, {'start': '2022-05-07T04:00:00Z','end': '2022-05-07T08:00:00Z' }], 'booked': [{'start': '2022-05-07T03:00:00Z', 'end': '2022-05-07T04:00:00Z' }]}"
        val coursesObject: Courses = Gson().fromJson(data, Courses::class.java)
        assertEquals("2022-05-07T00:30:00Z", coursesObject.available?.get(0)?.start)
    }

    @Test
    fun merge_timeline_from_Courses() {
        val data =
            "{'available': [{'start': '2022-05-07T00:30:00Z', 'end': '2022-05-07T02:00:00Z' }, {'start': '2022-05-07T04:00:00Z','end': '2022-05-07T05:00:00Z' }], 'booked': [{'start': '2022-05-06T03:00:00Z', 'end': '2022-05-06T04:00:00Z' }]}"
        val handledData = CourseUtilities.handle(
            Gson().fromJson(data, Courses::class.java)
        )
        // 3 + 2 + 2 = 7,
        assertEquals(handledData.size, 7)
        assertEquals(handledData[1].start, "2022-05-06T03:30:00Z")
    }

    @Test
    fun timezone_verify() {
        val dateStr = "2022-05-07T00:30:00Z"
        val formattedDate = DateUtility.transformToLocalTime(dateStr, TimeZone.getTimeZone("Asia/Taipei"))
        assertEquals(formattedDate.toString(), "2022-05-07T08:30:00Z")
    }
}