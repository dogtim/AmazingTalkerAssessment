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

package com.amazingtalker.assessment.views

import com.amazingtalker.assessment.adapter.CoursesAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.amazingtalker.assessment.R
import com.amazingtalker.assessment.data.Timeline

class CardView(layoutInflater: LayoutInflater, container: ViewGroup?) {
    val view: View = layoutInflater.inflate(R.layout.days_view, container, false)

    private var recyclerView: RecyclerView

    init {
        recyclerView = view.findViewById<RecyclerView>(R.id.courses_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }

    fun bind(card: List<Timeline>) {
        val adapter = CoursesAdapter(card)
        recyclerView.adapter = adapter
    }

}
