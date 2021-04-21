package com.mh.arclibrary.arch

import androidx.lifecycle.MutableLiveData

open class IState{
    val uiTrans = MutableLiveData<Int>()
}