package com.example.myschedule

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myschedule.Utils.daysInMonthArray
import com.example.myschedule.Utils.monthYearFromDate
import com.example.myschedule.Utils.selectedDate
import java.time.LocalDate



class CalendarActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecycler: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        monthYearText = findViewById(R.id.month)

        calendarRecycler = findViewById(R.id.calendarRecycler)

        selectedDate = LocalDate.now()
        setMonthView()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate!!)
        val daysInMonth = daysInMonthArray(selectedDate!!)
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecycler.layoutManager = layoutManager
        calendarRecycler.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View?) {
        selectedDate = selectedDate?.plusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction(view: View?) {
        selectedDate = selectedDate?.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, dayText: String?) {
        if (dayText != ""){
            val message = "Selected Day " + dayText + " " + monthYearFromDate(selectedDate!!)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    fun weeklyAction(view: View) {
        startActivity(Intent(this, WeekActivity::class.java))
    }
}