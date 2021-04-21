package com.mh.universalscroe.datacenter.api.service

import com.mh.universalscroe.datacenter.api.response.Member
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Response

interface MemberService{

    @GET("member")
    fun get(): Observable<Response<Member>>

}