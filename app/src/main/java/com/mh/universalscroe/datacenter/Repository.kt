package com.mh.universalscroe.datacenter

import com.mh.universalscroe.datacenter.api.HttpResult
import com.mh.universalscroe.datacenter.api.StatusCode
import com.mh.universalscroe.datacenter.api.response.ResponseStatus
import com.mh.universalscroe.datacenter.dataBean.MemberBean

import com.mh.universalscroe.datacenter.api.response.Member as ApiMember
import com.mh.universalscroe.datacenter.dataBean.MemberBean.Member as DataMember


import retrofit2.Retrofit

class Repository constructor(private val apiClient: Retrofit) {

    private val dataCenter = DataCenter()

    suspend fun getMember(): HttpResult<DataMember> =
        try {
            val result = test()
            if (result.statusCode.toInt() == StatusCode.Success){
                HttpResult.onSuccess(MemberBean().apiDataFilter(result.preload))
            }else{
                HttpResult.onError(result.statusCode.toInt(), result.message)
            }
        }catch (e: Throwable){
            HttpResult.onError(400, "Server error, try again please")
        }


    //模擬 api 回應資料
    private fun test(): ResponseStatus<ApiMember>{
        val perload = ApiMember("Kevin", 100)
        val result = ResponseStatus<ApiMember>("200", "success", perload)
        return result
    }
}