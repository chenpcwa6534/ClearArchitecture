package com.example.lib.extension

import androidx.lifecycle.MutableLiveData


fun <T: Any?> MutableLiveData<T>.default(value: T) = apply { setValue(value) }