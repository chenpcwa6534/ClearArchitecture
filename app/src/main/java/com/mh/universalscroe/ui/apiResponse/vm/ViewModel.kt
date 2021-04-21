package com.mh.universalscroe.ui.apiResponse.vm


import com.mh.universalscroe.base.BaseViewModel
import com.mh.universalscroe.ui.apiResponse.UIIntent
import com.mh.universalscroe.ui.apiResponse.UIState
import kotlinx.coroutines.channels.Channel

class ViewModel: BaseViewModel(){

    private val uiIntent: Channel<UIIntent> = Channel(Channel.UNLIMITED)
    private val uiState = UIState()

    companion object{
        fun getInstance() = ViewModel()
    }

    fun getUIState(): UIState = this.uiState
    fun getUIIntent(): Channel<UIIntent> = this.uiIntent

    init {

    }
}