package com.mh.universalscroe.datacenter.dataBean

import com.mh.universalscroe.datacenter.api.response.Member as ApiMember
import com.mh.universalscroe.datacenter.dataBean.MemberBean.Member as DataMember

class MemberBean{
    data class Member(
        val name: String,
        val age: Int
    )

    //將 api 資料轉成 app 內部需要資料
    fun apiDataFilter(response: ApiMember): DataMember{
        val result = DataMember(response.name, response.age)
        return result
    }
}