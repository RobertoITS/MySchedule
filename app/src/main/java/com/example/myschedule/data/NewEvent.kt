package com.example.myschedule.data

import java.time.LocalDate
import java.time.LocalTime

data class NewEvent(
    //Id seria la primaryKey, que se auto genere
    var id: Int?,
    var name: String?,
    var date: LocalDate?,
    var time: LocalTime?,
)