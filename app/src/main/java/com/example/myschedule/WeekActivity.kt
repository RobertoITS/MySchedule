package com.example.myschedule

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week)

        initWidgets()
        setWeekView()

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

    fun newEventAction(view: android.view.View) {}

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, dayText: String?) {
            val message = "Selected Day " + dayText + " " + monthYearFromDate(selectedDate!!)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}