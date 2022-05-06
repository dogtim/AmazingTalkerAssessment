package com.amazingtalker.assessment.data

import com.google.gson.annotations.Expose

// https://kotlinlang.org/docs/data-classes.html
data class Courses (
    @Expose
    var available: List<Timeline>? = null,
    @Expose
    var booked: List<Timeline>? = null
)

data class Timeline (
    @Expose
    var start: String? = null,
    @Expose
    var end: String? = null,
    var available: Boolean = true
)
