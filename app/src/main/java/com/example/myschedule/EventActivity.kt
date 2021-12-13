package com.example.myschedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myschedule.Utils.formattedDate
import com.example.myschedule.Utils.formattedTime
import com.example.myschedule.Utils.selectedDate
import com.example.myschedule.data.NewEventData
import com.example.myschedule.database.NewEventDb
import java.time.LocalTime

class EventActivity : AppCompatActivity() {

    private var eventName: EditText? = null
    private var eventDate: TextView? = null
    private var eventTime: TextView? = null
    private var id: Int? = null

    private lateinit var time: LocalTime

    var eventsList: ArrayList<NewEventData> = ArrayList()

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

    //Esta lista se guarda en la bd
    fun saveEventAction(view: View) {
//        val app = applicationContext as NewEventApp
        val db: NewEventDb = Room.databaseBuilder(applicationContext, NewEventDb::class.java, "newevent")
            .allowMainThreadQueries()
            .build()
        val eventNameString: String = eventName?.text.toString()
        val newEvent = NewEventData(id, eventNameString, selectedDate.toString(), time.toString())
        eventsList.add(newEvent)
        db.newEventDao().insert(eventsList)
        finish()
    }
}