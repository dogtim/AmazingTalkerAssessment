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

package com.amazingtalker.assessment.activity

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.amazingtalker.assessment.cards.CardViewAdapter
import androidx.viewpager2.widget.ViewPager2
import com.amazingtalker.assessment.DateUtility
import com.amazingtalker.assessment.R
import com.amazingtalker.assessment.data.CourseUtilities
import com.amazingtalker.assessment.data.Courses
import com.amazingtalker.assessment.databinding.ActivityTablayoutBinding
import com.amazingtalker.assessment.databinding.ItemCardLayoutBinding
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class CardViewTabLayoutActivity : FragmentActivity() {
    private val TAG = CardViewTabLayoutActivity::class.qualifiedName
    private lateinit var adapter: CardViewAdapter
    private lateinit var binding: ActivityTablayoutBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTablayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter = CardViewAdapter()

        binding.rightArrow.setOnClickListener {
            adapter.offset += 7;
            dataChanged()
        }

        binding.leftArrow.setOnClickListener {
            if (adapter.offset > 0) {
                adapter.offset -= 7;
                dataChanged()
            }
        }
        binding.weekTitle.text = DateUtility.getSevenString(adapter.offset)
        binding.viewPager.adapter = adapter
        network()

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            val adapter = binding.viewPager.adapter
            if (adapter is CardViewAdapter) {
                tab.text = DateUtility.getSubtitleDate(adapter.offset + position)
            }
        }.attach()
    }

    private fun dataChanged() {
        binding.weekTitle.text = DateUtility.getSevenString(adapter.offset)
        binding.viewPager.adapter?.notifyItemRangeChanged(0, 7)
    }

    private fun network() {
        val queue = Volley.newRequestQueue(this)
        val teacherName = "julia-shin"
        val url = "https://en.amazingtalker.com/v1/guest/teachers/" + teacherName + "/schedule?started_at=" + DateUtility.getSpecial()
        Log.d(TAG, "Request url: $url")
        val stringRequest = StringRequest(
            Request.Method.GET, url, { response ->
                val gson = Gson()
                val coursesObject: Courses = gson.fromJson(response, Courses::class.java)
                adapter.courses = CourseUtilities.handle(coursesObject)
                binding.viewPager.adapter?.notifyItemRangeChanged(0, 7)
            }, {
                Log.e("dogtim",  "That didn't work! " + it.message) }
        )
        queue.add(stringRequest)
    }
}
