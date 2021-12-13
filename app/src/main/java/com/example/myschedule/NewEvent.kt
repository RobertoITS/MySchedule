package com.example.myschedule

import java.time.LocalDate
import java.time.LocalTime

data class NewEvent(
    var name: String?,
    var date: LocalDate?,
    var time: LocalTime?,
)