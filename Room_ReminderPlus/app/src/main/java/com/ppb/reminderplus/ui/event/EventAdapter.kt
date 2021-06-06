package com.ppb.reminderplus.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ppb.reminderplus.R
import com.ppb.reminderplus.type.Event

class EventAdapter(private val listEvent: ArrayList<Event>) :
    RecyclerView.Adapter<EventAdapter.ListViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
            val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_event, viewGroup, false)
            return ListViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listEvent.size
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val event = listEvent[position]

            holder.eventName.text = event.nameOfEvent
            holder.startDate.text = "Start Date: " + event.startDates
            holder.endDate.text = "End Date:" + event.endDates
            holder.description.text = event.descriptions
        }
        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var eventName: TextView = itemView.findViewById(R.id.eventName)
            var startDate: TextView = itemView.findViewById(R.id.startDate)
            var endDate: TextView = itemView.findViewById(R.id.endDate)
            var description: TextView = itemView.findViewById(R.id.descriptionEvent)
        }

}