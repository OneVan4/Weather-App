package com.example.mashe4kinapogodaotivana.view

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
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

fun TextInputEditText.createObservable(): Flowable<String>{
    return Flowable.create({
        addTextChangedListener(object: SimpleTextWatcher(){
            override fun afterTextChanged(s:Editable?){
                it.onNext(s.toString())
            }
        })
    },BackpressureStrategy.BUFFER)
}

private abstract class SimpleTextWatcher:TextWatcher{
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {

    }
}