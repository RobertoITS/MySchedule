package com.example.myschedule.database

import androidx.room.Database
import androidx.room.RoomDatabase

//El encargado de gestionar la BD
@Database(entities = [NewEventData::class]
    , version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun newEventDao(): NewEventDao
}