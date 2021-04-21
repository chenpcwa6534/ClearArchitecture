package com.mh.universalscroe.ui.main.model

import com.mh.universalscroe.datacenter.api.HttpResult
import com.mh.universalscroe.datacenter.dataBean.MemberBean


interface IModel{
    suspend fun getMember(): HttpResult<MemberBean.Member>
}