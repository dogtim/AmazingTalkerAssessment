package com.amazingtalker.assessment.data

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CourseUtilities {

    companion object Merged {
        fun callMe(courses: Courses): ArrayList<Timeline> = run {
            val availableList = courses.available ?: ArrayList()
            val bookedList = courses.booked ?: ArrayList()
            val mergedList = ArrayList<Timeline>()
            var availableIterator = 0
            var bookedIterator = 0

            while (availableIterator < availableList.size || bookedIterator < bookedList.size) {
                if (availableIterator < availableList.size && bookedIterator < bookedList.size) {
                    val time1 = availableList[availableIterator]
                    val time2 = bookedList[bookedIterator]
                    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    val firstDate: Date = sdf.parse(time1.start!!) as Date
                    val secondDate: Date = sdf.parse(time2.start!!) as Date

                    val cmp = firstDate.compareTo(secondDate)
                    when {
                        cmp > 0 -> {
                            mergedList.add(bookedList[bookedIterator])
                            bookedIterator++
                        }
                        cmp < 0 -> {
                            mergedList.add(availableList[availableIterator])
                            availableIterator++;
                        }
                        else -> { // It should not happen in this condition
                            mergedList.add(availableList[availableIterator])
                            availableIterator++;
                            mergedList.add(bookedList[bookedIterator])
                            bookedIterator++
                        }
                    }
                } else if (availableIterator < availableList.size) {
                    mergedList.add(availableList[availableIterator])
                    availableIterator++;
                } else if (bookedIterator < bookedList.size) {
                    mergedList.add(bookedList[bookedIterator])
                    bookedIterator++
                }
            }
            return mergedList
        }
    }
}