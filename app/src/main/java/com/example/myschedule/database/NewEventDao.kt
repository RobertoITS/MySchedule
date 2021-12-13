package com.example.myschedule.database

import androidx.room.*

//Se encarga de las consultas
@Dao
interface NewEventDao {

    //Todos
    @Query("SELECT * FROM NewEventData")
    fun getAll(): List<NewEventData>

    //Inserta
    @Insert
    fun insert(newEventData: ArrayList<NewEventData>)

    //Actualiza
    @Update
    fun update(newEventData: NewEventData)

    //Borra
    @Delete
    fun delete(newEventData: NewEventData)
}