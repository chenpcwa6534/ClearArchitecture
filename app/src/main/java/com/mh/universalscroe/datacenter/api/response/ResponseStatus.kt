package com.mh.universalscroe.datacenter.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mh.universalscroe.datacenter.api.Contract

data class ResponseStatus<T>(
    @SerializedName(Contract.HttpQuery.Status.STATUS_CODE)
    @Expose
    var statusCode: String,
    @SerializedName(Contract.HttpQuery.Status.MESSAGE)
    @Expose
    var message: String = "",
    @SerializedName(Contract.HttpQuery.Status.PRELOAD)
    @Expose
    var preload: T
)