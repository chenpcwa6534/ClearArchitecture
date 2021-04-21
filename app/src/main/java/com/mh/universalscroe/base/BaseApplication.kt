package com.mh.universalscroe.base

import android.app.Application
import com.mh.universalscroe.di.apiClientScope
import com.mh.universalscroe.di.apiResponseModuleScope
import com.mh.universalscroe.di.mainViewModuleScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BaseApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin(){
        startKoin {
            androidContext(this@BaseApplication)
            modules(getKoinModules())
        }
    }

    private fun getKoinModules(): MutableList<Module>{
        val modules = mutableListOf<Module>()
        //Api Module
        modules.add(apiClientScope)

        //UI Module
        modules.add(mainViewModuleScope)
        modules.add(apiResponseModuleScope)

        return modules
    }

}