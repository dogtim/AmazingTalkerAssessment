package com.amazingtalker.assessment.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.amazingtalker.assessment.data.DateUtility
import com.amazingtalker.assessment.adapter.DayViewAdapter
import com.amazingtalker.assessment.apis.FetchTutorTime
import com.amazingtalker.assessment.data.Constant
import com.amazingtalker.assessment.data.CourseUtilities
import com.amazingtalker.assessment.data.Courses
import com.amazingtalker.assessment.databinding.ActivityTablayoutBinding
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import java.util.*

const val EXTRA_MESSAGE_TUTOR_NAME = "com.amazingtalker.assessment.extra"

class CalendarActivity : FragmentActivity() {
    private val TAG = CalendarActivity::class.qualifiedName
    private lateinit var adapter: DayViewAdapter
    private lateinit var binding: ActivityTablayoutBinding
    private lateinit var fetchTutorTime: FetchTutorTime

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTablayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupInteraction()
        setupAdapter()
        network()

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            val adapter = binding.viewPager.adapter
            if (adapter is DayViewAdapter) {
                tab.text = DateUtility.getSubtitleDate(adapter.offset + position)
            }
        }.attach()

    }

    private fun setupAdapter() {
        adapter = DayViewAdapter()

        binding.weekTitle.text = DateUtility.getSevenString(adapter.offset)

        val tz: TimeZone = TimeZone.getDefault()
        val timeZoneString = tz.id.toString() + ": " + tz.getDisplayName(false, TimeZone.SHORT)
        binding.timeZone.text = timeZoneString
        binding.viewPager.adapter = adapter
    }

    private fun setupInteraction() {
        binding.rightArrow.setOnClickListener {
            adapter.offset += Constant.SEVEN_DAYS
            dataChanged()
        }

        binding.leftArrow.setOnClickListener {
            if (adapter.offset > 0) {
                adapter.offset -= Constant.SEVEN_DAYS
                dataChanged()
            }
        }

        binding.textButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun dataChanged() {
        binding.weekTitle.text = DateUtility.getSevenString(adapter.offset)
        binding.viewPager.adapter?.notifyItemRangeChanged(0, Constant.SEVEN_DAYS)
    }

    private fun network() {
        intent.getStringExtra(EXTRA_MESSAGE_TUTOR_NAME)?.let {
            fetchTutorTime = FetchTutorTime(this, it)
            fetchTutorTime.fetch({ response ->
                val gson = Gson()
                val coursesObject: Courses = gson.fromJson(response, Courses::class.java)
                val tempList = CourseUtilities.handle(coursesObject)
                CourseUtilities.transformZone(tempList)
                adapter.courses = tempList
                binding.viewPager.adapter?.notifyItemRangeChanged(0, Constant.SEVEN_DAYS)
            }, {
                Log.e(TAG, "That didn't work! " + it.message)
            })
        }
    }

    override fun onStop() {
        super.onStop()
        fetchTutorTime?.cancel()
    }
}
