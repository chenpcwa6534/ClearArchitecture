package com.mh.mhLib.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mh.mhLib.R

class EasyRecyclerView @JvmOverloads constructor(context: Context, val attributeSet: AttributeSet? = null): RecyclerView(context, attributeSet){

    enum class LayoutManagerMode(val index: Int){
        VERTICAL(0),
        HORIZONTAL(1),
        GRID(2),
        HORIZONTAL_CENTER(3),
        VERTICAL_CENTER(4)
    }

    init {
        layoutManagerSetting()
    }

    private fun layoutManagerSetting(){
        val typedArray = this.context.obtainStyledAttributes(attributeSet, R.styleable.EasyRecyclerView)
        var mode = LayoutManagerMode.VERTICAL.index
        if (attributeSet != null) mode = typedArray.getInt(R.styleable.EasyRecyclerView_recycler__mode, 0)

        when(mode){
            LayoutManagerMode.VERTICAL.index -> this.layoutManager = getLinearLayoutManager(LinearLayoutManager.VERTICAL)
            LayoutManagerMode.HORIZONTAL.index -> this.layoutManager = getLinearLayoutManager(LinearLayoutManager.HORIZONTAL)
            LayoutManagerMode.VERTICAL_CENTER.index -> this.layoutManager = getCenterLinearLayoutManager(LinearLayoutManager.VERTICAL)
            LayoutManagerMode.HORIZONTAL_CENTER.index -> this.layoutManager = getCenterLinearLayoutManager(LinearLayoutManager.HORIZONTAL)
            LayoutManagerMode.GRID.index ->  {
                val column = typedArray.getInt(R.styleable.EasyRecyclerView_grid_column, 2)
                this.layoutManager = getGridViewLayoutManager(column)
            }
        }
    }

    private fun getLinearLayoutManager(direction: Int): androidx.recyclerview.widget.LinearLayoutManager {
        val lm = androidx.recyclerview.widget.LinearLayoutManager(context)
        lm.orientation = direction
        return lm
    }

    private fun getCenterLinearLayoutManager(direction: Int): CenterLayoutManager =
        CenterLayoutManager(this.context, direction, false)

    private fun getGridViewLayoutManager(column: Int) = androidx.recyclerview.widget.GridLayoutManager(context, column)
}