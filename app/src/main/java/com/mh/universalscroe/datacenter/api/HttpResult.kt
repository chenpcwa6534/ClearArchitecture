package com.mh.universalscroe.datacenter.api

sealed class HttpResult <out T: Any>{
    class onSuccess<out T: Any>(val data: T? = null): HttpResult<T>()
    class onError(val code: Int, val message: String): HttpResult<Nothing>()
}