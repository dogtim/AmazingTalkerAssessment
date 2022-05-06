package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.DateUtility
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CourseUtilities {

    companion object Merged {
        // Merge available and booked data first, and then sort these data
        fun handle(courses: Courses): List<Timeline> = run {
            val mergedList = ArrayList<Timeline>()

            val lambda = { a: List<Timeline>, available: Boolean ->
                for (item in a) {
                    item.available = available
                    item.end?.let { endTime ->
                        var temp = item.copy(
                            start = item.start,
                            end = item.end,
                            available = item.available)
                        while (temp.start != null && endTime != temp.start) {
                            mergedList.add(temp)
                            temp = item.copy(
                                start = DateUtility.addHalfHour(temp.start!!, 30),
                                end = item.end,
                                available = item.available
                            )
                        }
                    }
                }
            }

            courses.available?.let {
                lambda(it, true)
            }
            courses.booked?.let {
                lambda(it, false)
            }

            // Sort by start time
            val result = mergedList.sortedWith(compareBy {
                it.start
            })

            return result
        }
        fun transformZone(timelines: List<Timeline>) = run {
            for (item in timelines) {
                item.start?.let {
                    item.start = DateUtility.transformToLocalTime(it)
                }
                item.end?.let {
                    item.end = DateUtility.transformToLocalTime(it)
                }
            }
        }
    }
}