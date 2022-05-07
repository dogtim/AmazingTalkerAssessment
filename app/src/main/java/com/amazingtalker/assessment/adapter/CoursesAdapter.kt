package com.amazingtalker.assessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amazingtalker.assessment.data.DateUtility
import com.amazingtalker.assessment.R
import com.amazingtalker.assessment.data.Timeline

class CoursesAdapter(private val timeLines: List<Timeline>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_timeline, parent, false)
        return DesignViewHolder(view)
    }

    override fun onBindViewHolder(@Nullable holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DesignViewHolder) {
            val timeline = timeLines[position]
            val itemView = holder.itemView
            timeline.start?.let {
                holder.textView.text = DateUtility.hourAndMinutes(it)
            }
            val backgroundColor = if (timeline.available) R.color.green_100 else R.color.grey_800
            val textColor = if (timeline.available) R.color.green_300 else R.color.black
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, backgroundColor))
            holder.textView.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    textColor
                )
            )
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return timeLines.size
    }

    class DesignViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
