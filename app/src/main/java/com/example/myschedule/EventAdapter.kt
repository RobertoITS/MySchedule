package com.example.myschedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myschedule.Utils.formattedTime

class EventAdapter(context: Context, events: List<NewEvent>) : ArrayAdapter<NewEvent>(
    context, 0,
    events
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView2 = convertView
        val newEvent: NewEvent = getItem(position)!!
        if (convertView == null)
            convertView2 = LayoutInflater.from(context).inflate(R.layout.event_cell, parent, false)
        val eventCell: TextView = convertView!!.findViewById(R.id.eventCellTV)
        val eventTitle: String = newEvent.name + " " + formattedTime(newEvent.time!!)
        eventCell.text = eventTitle
        return convertView2!!
    }
}