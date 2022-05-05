package com.amazingtalker.assessment.data

// https://kotlinlang.org/docs/data-classes.html
data class Courses (
    var available: List<Timeline>? = null,
    var booked: List<Timeline>? = null
)

data class Timeline (
    var start: String? = null,
    var end: String? = null
)
