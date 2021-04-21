package com.mh.universalscroe.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.lib.extension.default
import com.mh.arclibrary.arch.IState

class UIState: IState(){
    //UI Data
    val name = MutableLiveData<String>()
    val age = MutableLiveData<Int>()

    val searchResult = MutableLiveData<String>()

    val msg = MutableLiveData<String>().default("Main")

    //Event
    data class ApiResult(
        val isShowProgress: Boolean,
        val result: Boolean = false,
        val errorMsg: String? = null
    )

    val apiResult = MutableLiveData<ApiResult>()
}