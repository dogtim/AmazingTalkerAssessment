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
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.integration.testapp.cards.CardViewAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * Base class for the two activities in the demo. Sets up the list of cards and implements UI to
 * jump to arbitrary cards using setCurrentItem, either with or without smooth scrolling.
 */
abstract class BaseCardActivity : FragmentActivity() {

    protected lateinit var viewPager: ViewPager2
    private lateinit var weekTitle: TextView
    private var offset = 0;
    private lateinit var rightArrowImage: ImageView
    private lateinit var leftArrowImage: ImageView

    protected open val layoutId: Int = R.layout.activity_no_tablayout

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewPager = findViewById(R.id.view_pager)

        weekTitle = findViewById(R.id.weekTitle)
        rightArrowImage = findViewById(R.id.rightArrow)
        leftArrowImage = findViewById(R.id.leftArrow)

        rightArrowImage.setOnClickListener {
            offset ++;
            weekTitle.text = DateUtility.getDateString(offset)
            viewPager.setCurrentItem(offset, true)
        }

        leftArrowImage.setOnClickListener {
            if (offset > 0) {
                offset--;
                weekTitle.text = DateUtility.getDateString(offset)
                viewPager.setCurrentItem(offset, true)
            }
        }

        weekTitle.text = DateUtility.getDateString(offset)
        viewPager.adapter = CardViewAdapter()
    }

}
