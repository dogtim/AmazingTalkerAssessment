package com.amazingtalker.assessment

import com.amazingtalker.assessment.data.Courses
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

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
    fun response_string_to_object2() {
        val generalInfoJson =
            "{'available': [{'start': '2022-05-07T00:30:00Z', 'end': '2022-05-07T03:00:00Z' }, {'start': '2022-05-07T04:00:00Z','end': '2022-05-07T08:00:00Z' }], 'booked': [{'start': '2022-05-07T03:00:00Z', 'end': '2022-05-07T04:00:00Z' }]}"
        val gson = Gson()
        val coursesObject: Courses = gson.fromJson(generalInfoJson, Courses::class.java)
        assertEquals("2022-05-07T00:30:00Z", coursesObject.available?.get(0)?.start)
    }

}