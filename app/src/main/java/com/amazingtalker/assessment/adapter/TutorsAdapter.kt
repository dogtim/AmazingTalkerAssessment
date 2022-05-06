package com.amazingtalker.assessment.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.amazingtalker.assessment.R
import com.amazingtalker.assessment.activity.CardViewTabLayoutActivity
import com.amazingtalker.assessment.activity.EXTRA_MESSAGE
import com.amazingtalker.assessment.data.Timeline

class TutorsAdapter(private val timeLines: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_view_timeline, parent, false)
        return DesignViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(@Nullable holder:  RecyclerView.ViewHolder, position: Int) {
        if (holder is DesignViewHolder) {
            val timeline = timeLines[position]
            holder.textView.text = timeline
            val context = holder.itemView.context

            holder.itemView.setOnClickListener {
                val intent = Intent(context, CardViewTabLayoutActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, timeline)
                }

                context.startActivity(intent)
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
