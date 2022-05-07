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
            var itemView = holder.itemView
            timeline.start?.let {
                holder.textView.text = DateUtility.hourAndMinutes(it)
            }
            if (timeline.available) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green_100))
                holder.textView.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.green_300
                    )
                )
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.grey_800))
                holder.textView.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.black
                    )
                )
            }

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
