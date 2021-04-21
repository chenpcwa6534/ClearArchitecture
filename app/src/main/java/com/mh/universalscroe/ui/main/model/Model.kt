package com.mh.universalscroe.ui.main.model

import com.mh.universalscroe.base.BaseModel
import com.mh.universalscroe.datacenter.Repository
import com.mh.universalscroe.datacenter.api.HttpResult
import com.mh.universalscroe.datacenter.dataBean.MemberBean.Member

class Model constructor(private val repository: Repository): BaseModel(), IModel {

    private val memberInfo: Member? = null

    companion object {
        fun getInstance(repository: Repository) = Model(repository)
    }

    override suspend fun getMember(): HttpResult<Member> =
        if (memberInfo != null) {
            HttpResult.onSuccess(this.memberInfo)
        } else {
            this.repository.getMember()
        }
}