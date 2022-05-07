package com.amazingtalker.assessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amazingtalker.assessment.data.DateUtility
import com.amazingtalker.assessment.views.CardView
import com.amazingtalker.assessment.data.Timeline
import java.text.SimpleDateFormat
import java.util.*

class DayViewAdapter : RecyclerView.Adapter<CardViewHolder>() {
    var offset = 0;
    var courses: List<Timeline>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(CardView(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        courses?.let {

            val day = DateUtility.getDayByFormat(
                offset + position,
                SimpleDateFormat(DateUtility.DATE_PATTERN_1, Locale.getDefault())
            )
            val filteredValuesMap = it.filter { it ->
                it.start?.contains(day) ?: false
            }

            holder.bind(filteredValuesMap)
        }
    }

    // The count of days in week
    override fun getItemCount(): Int {
        return 7
    }
}

class CardViewHolder internal constructor(private val cardView: CardView) :
    RecyclerView.ViewHolder(cardView.view) {
    internal fun bind(card: List<Timeline>) {
        cardView.bind(card)
    }
}
