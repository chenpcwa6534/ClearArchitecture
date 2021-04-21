package com.mh.mhLib.recyclerview

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CenterLayoutManager constructor(context: Context, orientation: Int = RecyclerView.VERTICAL, reverseLayout: Boolean = false): LinearLayoutManager(context, orientation, reverseLayout){

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
//        super.smoothScrollToPosition(recyclerView, state, position)
        val smoothScroller = CenterSmoothScroller(recyclerView!!.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private class CenterSmoothScroller constructor(context: Context): LinearSmoothScroller(context){
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float =
            100f / displayMetrics!!.densityDpi

        override fun calculateDtToFit(viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int): Int =
            (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)

    }
}