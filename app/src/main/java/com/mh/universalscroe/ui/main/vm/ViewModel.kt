package com.mh.universalscroe.ui.main.vm

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.mh.universalscroe.R
import com.mh.universalscroe.base.BaseViewModel
import com.mh.universalscroe.datacenter.api.HttpResult
import com.mh.universalscroe.datacenter.dataBean.MemberBean
import com.mh.universalscroe.ui.main.UIIntent
import com.mh.universalscroe.ui.main.UIState
import com.mh.universalscroe.ui.main.model.Model
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel constructor(private val context: Context, private val model: Model): BaseViewModel(){

    private val uiIntent: Channel<UIIntent> = Channel(Channel.UNLIMITED)
    private val uiState = UIState()

    companion object{
        fun getInstance(context: Context, model: Model) = ViewModel(context, model)
    }

    fun getUIState(): UIState = this.uiState
    fun getUIIntent(): Channel<UIIntent> = this.uiIntent


    init {
        handlerIntent()
    }

    private fun handlerIntent(){
        viewModelScope.launch {
            this@ViewModel.uiIntent.consumeAsFlow().collect { uiIntent ->
                when(uiIntent){
                    UIIntent.Next -> next()
                    UIIntent.Research -> research()
                    UIIntent.Refresh -> refresh()
                }
            }
        }
    }

    private fun next(){
        this.uiState.uiTrans.postValue(R.id.action_mainFragment_to_apiResponseFragment)
    }

    private fun research(){

    }

    private fun refresh(){
        CoroutineScope(Dispatchers.IO).launch {
            this@ViewModel.uiState.apiResult.postValue(UIState.ApiResult(true))
            val response = this@ViewModel.model.getMember()
            withContext(Dispatchers.Main){
                when(response){
                    is HttpResult.onSuccess -> updateUI(response.data)
                    is HttpResult.onError -> this@ViewModel.uiState.apiResult.postValue(UIState.ApiResult(false, false, response.message))
                }
            }
        }
    }

    private fun updateUI(data: MemberBean.Member?){
        this.uiState.name.postValue(data?.name)
        this.uiState.age.postValue(data?.age)
        this.uiState.apiResult.postValue(UIState.ApiResult(false, true))
    }
}