package com.amazingtalker.assessment.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.amazingtalker.assessment.DateUtility
import com.amazingtalker.assessment.cards.CardViewAdapter
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
            adapter.offset += 7
            dataChanged()
        }

        binding.leftArrow.setOnClickListener {
            if (adapter.offset > 0) {
                adapter.offset -= 7
                dataChanged()
            }
        }
        binding.weekTitle.text = DateUtility.getSevenString(adapter.offset)
0
        val tz: TimeZone = TimeZone.getDefault()
        val timeZoneString = tz.id.toString() + ": " + tz.getDisplayName(false, TimeZone.SHORT)
        binding.timeZone.text = timeZoneString
        binding.viewPager.adapter = adapter
        network()

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            val adapter = binding.viewPager.adapter
            if (adapter is CardViewAdapter) {
                tab.text = DateUtility.getSubtitleDate(adapter.offset + position)
            }
        }.attach()


        binding.textButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun dataChanged() {
        binding.weekTitle.text = DateUtility.getSevenString(adapter.offset)
        binding.viewPager.adapter?.notifyItemRangeChanged(0, 7)
    }

    private fun network() {
        val queue = Volley.newRequestQueue(this)
        val name = intent.getStringExtra(EXTRA_MESSAGE_TUTOR_NAME)
        val teacherName = name
        val url = "https://en.amazingtalker.com/v1/guest/teachers/" + teacherName + "/schedule?started_at=" + DateUtility.getSpecial()
        Log.d(TAG, "Request url: $url")
        val stringRequest = StringRequest(
            Request.Method.GET, url, { response ->
                val gson = Gson()
                val coursesObject: Courses = gson.fromJson(response, Courses::class.java)
                val tempList = CourseUtilities.handle(coursesObject)
                CourseUtilities.transformZone(tempList)
                adapter.courses = tempList
                binding.viewPager.adapter?.notifyItemRangeChanged(0, 7)
            }, {
                Log.e("dogtim",  "That didn't work! " + it.message) }
        )
        queue.add(stringRequest)
    }
}
