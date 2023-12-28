package com.example.mashe4kinapogodaotivana.view

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

const val DAY_FULL_MONTH_NAME ="dd MMM"
const val DAY_WEEK_NAME_LONG ="dd EEEE"
const val HOUR_DOUBLE_DOT_MINUTE ="HH:mm"

fun Long.toDateFormatOF(format:String):String {
    val calendar = Calendar.getInstance()
    val timeZone = calendar.timeZone
    val SDF = SimpleDateFormat(format,Locale.getDefault())
    SDF.timeZone=timeZone
    return SDF.format(Date(this*1000))
}

fun Double.toDegree()=(this-273.15).roundToInt().toString()

fun Double.toPercentString(extraPart:String ="") =(this*100).roundToInt().toString()+extraPart

fun String.provideIcon() ="" //TODO Доделай отображение иконок