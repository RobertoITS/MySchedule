package com.example.myschedule.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

//Usando Room
//Creamos la entidad
@Entity
data class NewEvent(
    //Id seria la primaryKey, que se auto genere
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name: String?,
    var date: LocalDate?,
    var time: LocalTime?,
)