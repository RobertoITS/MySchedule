package com.example.myschedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myschedule.Utils.formattedDate
import com.example.myschedule.Utils.formattedTime
import com.example.myschedule.Utils.selectedDate
import java.time.LocalTime

class EventActivity : AppCompatActivity() {

    private var eventName: EditText? = null
    private var eventDate: TextView? = null
    private var eventTime: TextView? = null

    private lateinit var time: LocalTime

    var eventsList: ArrayList<NewEvent> = ArrayList()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        initWidget()

        time = LocalTime.now()

        eventDate?.text = ("Date: " + formattedDate(selectedDate!!))

        eventTime?.text = ("Time: " + formattedTime(time))
    }

    private fun initWidget() {
        eventName = findViewById(R.id.eventName)
        eventDate = findViewById(R.id.eventDate)
        eventTime = findViewById(R.id.eventTime)
    }

    //Desde base de datos:
    //Esta lista se guarda
    fun saveEventAction(view: View) {
        val eventNameString: String = eventName?.text.toString()
        val newEvent = NewEvent(eventNameString, selectedDate, time)
        eventsList.add(newEvent)
        finish()
    }
}