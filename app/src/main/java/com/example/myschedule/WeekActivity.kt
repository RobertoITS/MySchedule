package com.example.myschedule

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myschedule.Utils.daysInWeekArray
import com.example.myschedule.Utils.monthYearFromDate
import com.example.myschedule.Utils.selectedDate
import java.time.LocalDate

class WeekActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private var monthYearText: TextView? = null
    private var calendarRecycler: RecyclerView? = null
    private var eventList: ListView? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week)

        initWidgets()
        setWeekView()
        setEventAdapter()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeekView() {
        monthYearText?.text = monthYearFromDate(selectedDate!!)
        val days: ArrayList<LocalDate?>? = daysInWeekArray(selectedDate!!)
        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecycler?.layoutManager = layoutManager
        calendarRecycler?.adapter = calendarAdapter
    }

    private fun initWidgets() {
        calendarRecycler = findViewById(R.id.calendarRecycler)
        monthYearText = findViewById(R.id.month)
        eventList = findViewById(R.id.eventList)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextWeekAction(view: android.view.View) {
        selectedDate = selectedDate?.plusWeeks(1)
        setWeekView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousWeekAction(view: android.view.View) {
        selectedDate = selectedDate?.minusWeeks(1)
        setWeekView()
    }

    fun newEventAction(view: android.view.View) {
        startActivity(Intent(this, EventActivity::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate) {
        selectedDate = date
        setWeekView()
    }

    override fun onResume() {
        super.onResume()
        setEventAdapter()
    }

    //Este adaptador tiene que sacar la info desde la base de datos
    private fun setEventAdapter() {
        val daylyEvents: List<NewEvent> = eventsForDate(selectedDate!!)
        val eventAdapter = EventAdapter(applicationContext, daylyEvents)
        eventList?.adapter = eventAdapter
    }

    @SuppressLint("NewApi")
    fun eventsForDate(date: LocalDate): ArrayList<NewEvent> {
        val events: ArrayList<NewEvent> = ArrayList()
        for (newEvent: NewEvent in EventActivity().eventsList) {
            if (newEvent.date == date) events.add(newEvent)
        }
        return events
    }

}