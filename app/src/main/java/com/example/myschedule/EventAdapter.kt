package com.example.myschedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myschedule.data.NewEventData

//adaptador para la lista de eventos nuevos

class EventAdapter(private val newEventList: ArrayList<NewEventData>): RecyclerView.Adapter<EventAdapter.EventViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventAdapter.EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_cell, parent, false)
        return EventViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        val currentEvent = newEventList[position]
        holder.eventCell.text = currentEvent.name + " " + currentEvent.date + " " + currentEvent.time
    }

    override fun getItemCount(): Int {
        return newEventList.size
    }

    class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val eventCell: TextView = itemView.findViewById(R.id.eventCell)
    }

}

//class EventAdapter(context: Context, events: List<NewEvent>) : ArrayAdapter<NewEvent>(
//    context, 0,
//    events
//) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var convertView2 = convertView
//        val newEvent: NewEvent = getItem(position)!!
//        if (convertView == null)
//            convertView2 = LayoutInflater.from(context).inflate(R.layout.event_cell, parent, false)
//        val eventCell: TextView = convertView!!.findViewById(R.id.eventCellTV)
//        val eventTitle: String = newEvent.name + " " + formattedTime(newEvent.time!!)
//        eventCell.text = eventTitle
//        return convertView2!!
//    }
//}