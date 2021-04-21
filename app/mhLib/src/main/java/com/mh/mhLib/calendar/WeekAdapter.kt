package com.mh.mhLib.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mh.mhLib.BR
import com.mh.mhLib.R
import com.mh.mhLib.tools.*
import java.util.*

class WeekAdapter(private val context: Context, private val days: MutableList<Date>, private val listener: OnItemClickListener? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var datatimeFormat = "yyyy-MM-dd"
        set(value){ field = value }
        get() = field

    private val ITEMTYPE_TODAY = 0
    private val ITEMTYPE_OTHER = 1

    interface OnItemClickListener{
        fun onItemClick(date: Date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            ITEMTYPE_TODAY -> ViewHolderEmpty(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.calendar_item_week_day_today, parent, false))
            else -> ViewHolderEmpty(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.calendar_item_week_day_other, parent, false))
        }

    override fun getItemCount(): Int = this.days.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ViewHolderEmpty).binding
        binding.setVariable(BR.date, this.days[position].toString(datatimeFormat))
        binding.setVariable(BR.week, getWeek(this.days[position]))
        binding.root.setOnClickListener {
            if (listener != null) this.listener.onItemClick(this.days[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isToday(this.days[position])) ITEMTYPE_TODAY
        else ITEMTYPE_OTHER
    }

    private fun getWeek(date: Date): String =
        this.context.getString(date.getWeekRes())

    private fun isToday(date: Date): Boolean{
        val today = System.currentTimeMillis()
        return (today.getYear() == date.time.getYear()).and(today.getMonth() == date.time.getMonth()).and(today.getDay() == date.time.getDay())
    }

    private class ViewHolderEmpty(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)


}