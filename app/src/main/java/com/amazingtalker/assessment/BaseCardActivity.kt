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
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.integration.testapp.cards.Card
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL

/**
 * Base class for the two activities in the demo. Sets up the list of cards and implements UI to
 * jump to arbitrary cards using setCurrentItem, either with or without smooth scrolling.
 */
abstract class BaseCardActivity : FragmentActivity() {

    protected lateinit var viewPager: ViewPager2
    private lateinit var cardSelector: Spinner
    private lateinit var gotoPage: Button

    protected open val layoutId: Int = R.layout.activity_no_tablayout

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewPager = findViewById(R.id.view_pager)
        cardSelector = findViewById(R.id.card_spinner)
        gotoPage = findViewById(R.id.jump_button)

        cardSelector.adapter = createCardAdapter()

        gotoPage.setOnClickListener {
            val card = cardSelector.selectedItemPosition
            val smoothScroll = true
            viewPager.setCurrentItem(card, smoothScroll)
        }
    }

    private fun createCardAdapter(): SpinnerAdapter {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Card.DECK)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }
}
