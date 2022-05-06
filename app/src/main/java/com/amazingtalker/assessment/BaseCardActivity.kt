/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazingtalker.assessment

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.amazingtalker.assessment.cards.CardViewAdapter
import androidx.viewpager2.widget.ViewPager2
import com.amazingtalker.assessment.data.CourseUtilities
import com.amazingtalker.assessment.data.Courses
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

/**
 * Base class for the two activities in the demo. Sets up the list of cards and implements UI to
 * jump to arbitrary cards using setCurrentItem, either with or without smooth scrolling.
 */
abstract class BaseCardActivity : FragmentActivity() {

    protected lateinit var viewPager: ViewPager2
    private lateinit var weekTitle: TextView

    private lateinit var rightArrowImage: ImageView
    private lateinit var leftArrowImage: ImageView
    private lateinit var adapter: CardViewAdapter

    protected open val layoutId: Int = R.layout.activity_no_tablayout

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        adapter = CardViewAdapter()
        viewPager = findViewById(R.id.view_pager)

        weekTitle = findViewById(R.id.weekTitle)
        rightArrowImage = findViewById(R.id.rightArrow)
        leftArrowImage = findViewById(R.id.leftArrow)

        rightArrowImage.setOnClickListener {
            adapter.offset += 7;
            dataChanged()
        }

        leftArrowImage.setOnClickListener {
            if (adapter.offset > 0) {
                adapter.offset -= 7;
                dataChanged()
            }
        }

        weekTitle.text = DateUtility.getSevenString(adapter.offset)
        viewPager.adapter = adapter
        network()
    }

    private fun dataChanged() {
        weekTitle.text = DateUtility.getSevenString(adapter.offset)
        viewPager.adapter?.notifyItemRangeChanged(0, 7)
    }

    private fun network() {
        val queue = Volley.newRequestQueue(this)
        var url1 = "https://en.amazingtalker.com/v1/guest/teachers/julia-shin/schedule?started_at=2022-04-30T16%3A00%3A00.000Z"
        val url = "https://en.amazingtalker.com/v1/guest/teachers/julia-shin/schedule?started_at=" + DateUtility.getSpecial()

        val stringRequest = StringRequest(
            Request.Method.GET, url, { response ->
                // Display the first 500 characters of the response string.
                val gson = Gson()
                val coursesObject: Courses = gson.fromJson(response, Courses::class.java)
                //assertEquals("2022-05-07T00:30:00Z", )
                Log.i("dogtim", "url: " + url)
                adapter.courses = CourseUtilities.callMe(coursesObject)
                coursesObject.available?.get(0)?.start?.let { Log.i("dogtim", "what " + it) }
            }, {
                Log.e("dogtim",  "That didn't work! " + it.message) }
        )

        queue.add(stringRequest)
    }
}
