package com.mh.mhLib.calendar

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.mh.mhLib.R
import kotlinx.android.synthetic.main.calendar_month.view.txt_header as MonthHeader
import kotlinx.android.synthetic.main.calendar_month.view.week_header as MonthWeekHeader
import kotlinx.android.synthetic.main.calendar_week.view.txt_header as WeekHeader
import kotlinx.android.synthetic.main.calendar_week.view.*
import java.util.*

@Suppress("DEPRECATION")
class CalendarView @JvmOverloads constructor(context: Context, val attributeSet: AttributeSet? = null): FrameLayout(context, attributeSet), WeekAdapter.OnItemClickListener{

    private val typedArray =  this.context.obtainStyledAttributes(attributeSet, R.styleable.CalendarView)
    private val mode: Mode = Mode.valueOf(typedArray.getInt(R.styleable.CalendarView_calendar_mode, 0))
    private val header_size = typedArray.getDimension(R.styleable.CalendarView_header_size, getDimension(R.dimen.default_header_text))
    private val header_color = typedArray.getColor(R.styleable.CalendarView_header_color, getColor(R.color.default_week_text))
    private val header_background = typedArray.getDrawable(R.styleable.CalendarView_header_background)
    private val weekTextColor = typedArray.getColor(R.styleable.CalendarView_week_title_color, getColor(R.color.default_week_text))
    private val weekTextSize = typedArray.getDimension(R.styleable.CalendarView_week_title_size, getDimension(R.dimen.default_week_text))
    private val weekBackground = typedArray.getDrawable(R.styleable.CalendarView_week_background)
    private val weekStartDay = WeekHeaderView.WeekDay.valueOf(typedArray.getInt(R.styleable.CalendarView_week_start, 0))

    private val limitBefore = typedArray.getInt(R.styleable.CalendarView_limit_before, 0)
    private val limitAfter = typedArray.getInt(R.styleable.CalendarView_limit_after, 0)
    private val preloadDateLength = typedArray.getInt(R.styleable.CalendarView_preload_day_length, Config.DEFAULT_PRELOAD_DAY_LENGTH)

    private var onClickListener: OnClickListener? = null

    interface OnClickListener{
        fun onDaySelected(time: Long, year: Int, month: Int, day: Int)
    }

    enum class Mode(val code: Int){
        MONTH(0),
        WEEK(1);

        companion object{
            fun valueOf(code: Int): Mode =
                when(code){
                    MONTH.code -> MONTH
                    else -> WEEK
                }
        }
    }

    init {
        onCreateView()
    }

    override fun onItemClick(date: Date) {
        if (this.onClickListener != null){
            this.onClickListener!!.onDaySelected(date.time, date.year, date.month, date.day)
        }
    }

    private fun onCreateView(){
        this.addView(LayoutInflater.from(this.context).inflate(getViewRes(), null))
        initViewContent()
    }

    private fun getViewRes() =
        when(mode){
            Mode.MONTH -> R.layout.calendar_month
            else -> R.layout.calendar_week
        }

    private fun initViewContent(){
        when(mode){
            Mode.MONTH -> initMonthMode()
            else -> initWeekMode()
        }
    }

    private fun initMonthMode(){
        initMonthModeView()
    }

    private fun initWeekMode(){
        initWeekModeView()
        initWeekModeData()
        viewTreeObserver.addOnGlobalLayoutListener {
            list_week.smoothScrollToPosition(getCenterIndex())
        }
    }

    private fun initMonthModeView(){
        MonthHeader.setTextColor(header_color)
        MonthHeader.textSize = header_size
        MonthHeader.background = header_background

        MonthWeekHeader.textColor = weekTextColor
        MonthWeekHeader.textSize = weekTextSize
        MonthWeekHeader.background = weekBackground
        MonthWeekHeader.startWeekDay = weekStartDay
    }

    private fun initWeekModeView(){
        WeekHeader.setTextColor(header_color)
        WeekHeader.textSize = header_size
        WeekHeader.background = header_background
    }

    private fun initWeekModeData(){
        val calendar = Calendar.getInstance()
        WeekHeader.text = "${calendar.get(Calendar.YEAR)} ${this.context.getString(R.string.year)}"
        val adapter = WeekAdapter(this.context, getWeekList(), this)
        list_week.adapter = adapter
    }

    private fun getWeekList(): MutableList<Date>{
        val currentDayIndex = (this.preloadDateLength / 2) + 1
        val days = mutableListOf<Date>()
        for (i in 0 until this.preloadDateLength){
            days.add(
                when {
                    i < currentDayIndex -> getBeforeDay(currentDayIndex - i)
                    i == currentDayIndex -> getCurrentDay()
                    else -> getAfterDay(i - currentDayIndex)
                }
            )
        }
        return days
    }

    private fun getBeforeDay(day: Int): Date{
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, day * -1)
        return calendar.time
    }

    private fun getAfterDay(day: Int): Date{
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, day)
        return calendar.time
    }

    private fun getCurrentDay(): Date = Calendar.getInstance().time

    private fun getDimension(res: Int): Float =
        this.context.resources.getDimension(res)

    private fun getColor(res: Int): Int =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) this.context.resources.getColor(res, this.context.theme)
        else this.context.resources.getColor(res)

    private fun getDrawable(res: Int): Drawable? =
        this.context.getDrawable(res)

    private fun getCenterIndex(): Int =
        if ((this.preloadDateLength % 2) == 0) this.preloadDateLength / 2
        else (this.preloadDateLength / 2) + 1


    fun setListener(l: OnClickListener){ this.onClickListener = l }
}