package com.mh.universalscroe.base

interface BaseViewModelImpl<S>{
    fun getUIState(): S
}