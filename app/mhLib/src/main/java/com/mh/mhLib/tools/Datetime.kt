package com.mh.mhLib.tools

import com.mh.mhLib.R
import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(format: String): String =
    this.time.toString(format)

fun Long.toString(format: String): String{
    val sdf = SimpleDateFormat(format)
    return sdf.format(this)
}

fun Date.getWeekRes(): Int =
    this.time.getWeekRes()

fun Long.getWeekRes(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    return when(calendar.get(Calendar.DAY_OF_WEEK)){
        Calendar.SATURDAY -> R.string.sunday
        Calendar.MONDAY -> R.string.monday
        Calendar.TUESDAY -> R.string.tuesday
        Calendar.WEDNESDAY -> R.string.wednesday
        Calendar.THURSDAY -> R.string.thursday
        Calendar.FRIDAY -> R.string.friday
        else -> R.string.saturday
    }
}

fun Date.getYear(): Int =
    this.time.getYear()

fun Long.getYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.YEAR)
}

fun Date.getMonth(): Int =
    this.time.getMonth()

fun Long.getMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.MONTH)
}

fun Date.getDay(): Int =
    this.time.getDay()

fun Long.getDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.DAY_OF_MONTH)
}