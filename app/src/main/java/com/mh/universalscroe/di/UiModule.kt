package com.mh.universalscroe.di

import com.mh.universalscroe.datacenter.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

import com.mh.universalscroe.ui.main.vm.ViewModel as MainViewModel
import com.mh.universalscroe.ui.main.model.Model as MainModel

import com.mh.universalscroe.ui.apiResponse.vm.ViewModel as ApiResponseViewModel
import com.mh.universalscroe.ui.apiResponse.model.Model as ApiResponseModel

val mainViewModuleScope = module{
    fun provideMainModel(repository: Repository): MainModel = MainModel.getInstance(repository)

    single { provideMainModel(get()) }
    factory { MainViewModel.getInstance(androidContext(), get()) }
}

val apiResponseModuleScope = module {
    fun provideApiResponseModel(): ApiResponseModel = ApiResponseModel.getInstance()

    single { provideApiResponseModel() }
    factory { ApiResponseViewModel.getInstance() }
}