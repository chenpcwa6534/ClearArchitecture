package com.mh.mhLib.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.mh.mhLib.R
import kotlinx.android.synthetic.main.calendar_week_header.view.*

class WeekHeaderView @JvmOverloads constructor(context: Context, val attributeSet: AttributeSet? = null): FrameLayout(context, attributeSet){

    var textSize: Float = 20f
        set(value) { field = value }
        get() = field

    var textColor: Int = R.color.default_week_text
        set(value) { field = value }
        get() = field

    var startWeekDay: WeekDay = WeekDay.SUNDAY
        set(value) {
            initWeek(value)
            field = value
        }
        get() = field

    enum class WeekDay(val code: Int, val res: Int){
        MONDAY(0, R.string.monday),
        TUESDAY(1, R.string.tuesday),
        WEDNESDAY(2, R.string.wednesday),
        THURSDAY(3, R.string.thursday),
        FRIDAY(4, R.string.friday),
        SATURDAY(5, R.string.saturday),
        SUNDAY(6, R.string.sunday);

        companion object{
            fun valueOf(code: Int): WeekDay =
                when(code){
                    MONDAY.code -> MONDAY
                    TUESDAY.code -> TUESDAY
                    WEDNESDAY.code -> WEDNESDAY
                    THURSDAY.code -> THURSDAY
                    FRIDAY.code -> FRIDAY
                    SATURDAY.code -> SATURDAY
                    else -> SUNDAY
                }
        }
    }

    init {
        onCreateView()
    }

    private fun onCreateView(){
        this.addView(LayoutInflater.from(this.context).inflate(R.layout.calendar_week_header, null))
        initWeek(this.startWeekDay)
    }

    private fun initWeek(weekStart: WeekDay){
        this.ly_week_header.removeAllViews()
        val weeks = filterWeeks(weekStart)
        for (i in 0 until weeks.size){
            val textView = createWeekTitle()
            textView.text = weeks[i]
            this.ly_week_header.addView(textView)
        }
    }

    private fun filterWeeks(weekStart: WeekDay): MutableList<String>{
        val filterWeeks = mutableListOf<String>()
        for (i in weekStart.code until WeekDay.values().size + weekStart.code){
            filterWeeks.add(this.context.getString(
                if (i > 6) WeekDay.values()[i - 7].res
                else WeekDay.values()[i].res
            ))
        }
        return filterWeeks
    }

    private fun createWeekTitle(): TextView{
        val textView = TextView(this.context)
        textView.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.setTextColor(textColor)
        textView.textSize = textSize
        return textView
    }
}