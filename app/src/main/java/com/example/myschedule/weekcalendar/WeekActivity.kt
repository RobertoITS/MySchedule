package com.example.myschedule.weekcalendar

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myschedule.events.EventActivity
import com.example.myschedule.utils.Utils.daysInWeekArray
import com.example.myschedule.utils.Utils.monthYearFromDate
import com.example.myschedule.utils.Utils.selectedDate
import com.example.myschedule.database.NewEventData
import com.example.myschedule.database.AppDataBase
import com.example.myschedule.databinding.ActivityWeekBinding
import com.example.myschedule.monthcalendar.adapter.CalendarAdapter
import com.example.myschedule.weekcalendar.adapter.EventAdapter
import java.time.LocalDate

class WeekActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var newEvent: ArrayList<NewEventData>
    private lateinit var binding: ActivityWeekBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWeekBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //The recycler view of the saved events and his layout manager
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.eventList.layoutManager = llm

        setWeekView()
        setEventAdapter()

        //Button previous week and his functions
        binding.previousWeekBtn.setOnClickListener {
            previousWeekAction()
        }
        //Button next week and his functions
        binding.nextWeekBtn.setOnClickListener {
            nextWeekAction()
        }
        //Button for add new events
        binding.newEventBtn.setOnClickListener {
            newEventAction()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeekView() {
        binding.month.text = monthYearFromDate(selectedDate!!)
        val days: ArrayList<LocalDate?>? = daysInWeekArray(selectedDate!!)
        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        binding.calendarRecycler.layoutManager = layoutManager
        binding.calendarRecycler.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextWeekAction() {
        selectedDate = selectedDate?.plusWeeks(1)
        setWeekView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousWeekAction() {
        selectedDate = selectedDate?.minusWeeks(1)
        setWeekView()
    }

    fun newEventAction() {
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

    //This adapter bring the info form the data base
    private fun setEventAdapter() {
        //We instantiate the data base reference
        val db: AppDataBase = Room.databaseBuilder(applicationContext,
            AppDataBase::class.java,
            "NewEventData")
            .allowMainThreadQueries()
            .build()
        newEvent = arrayListOf()
        //Functions to bring all the data from the data base
        newEvent = db.newEventDao().getAll() as ArrayList<NewEventData>
        binding.eventList.adapter = EventAdapter(newEvent, applicationContext)
    }

//    @SuppressLint("NewApi")
//    fun eventsForDate(date: LocalDate): ArrayList<NewEvent> {
//        val events: ArrayList<NewEvent> = ArrayList()
//        for (newEvent: NewEvent in EventActivity().eventsList) {
//            if (newEvent.date == date) events.add(newEvent)
//        }
//        return events
//    }

}