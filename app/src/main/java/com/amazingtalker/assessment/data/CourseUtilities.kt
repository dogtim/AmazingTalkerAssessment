package com.amazingtalker.assessment.data

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
                    mergedList.add(item)
                }
            }

            courses.available?.let {
                lambda(it, true)
            }
            courses.booked?.let {
                lambda(it, false)
            }

            val result = mergedList.sortedWith(compareBy {
                it.start
            })

            return result
        }
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
                            var item = bookedList[bookedIterator]
                            item.available = false
                            mergedList.add(item)
                            bookedIterator++
                        }
                        cmp < 0 -> {
                            var item = bookedList[availableIterator]
                            item.available = true
                            mergedList.add(item)
                            availableIterator++;
                        }
                        else -> { // It should not happen in this condition
                            var item = bookedList[availableIterator]
                            item.available = true
                            mergedList.add(item)
                            availableIterator++;
                            item = bookedList[bookedIterator]
                            item.available = false
                            mergedList.add(item)
                            bookedIterator++
                        }
                    }
                } else if (availableIterator < availableList.size) {
                    var item = bookedList[availableIterator]
                    item.available = true
                    mergedList.add(item)
                    availableIterator++;
                } else if (bookedIterator < bookedList.size) {
                    var item = bookedList[bookedIterator]
                    item.available = false
                    mergedList.add(item)
                    bookedIterator++
                }
            }
            return mergedList
        }
    }
}