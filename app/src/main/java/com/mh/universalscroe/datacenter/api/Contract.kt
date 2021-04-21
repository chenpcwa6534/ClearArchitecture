package com.mh.universalscroe.datacenter.api


class Contract{

    val connectTimeout: Long = 30

    private val hostUrlForRelease = "https://us.k33uc.com/api/v1/"
    private val hostUrlForDeveloper = "https://usdev.k33uc.com/api/v1/"
    private val hostUrlForBate ="https://us.k33uc.com/api/v1/"

    enum class Env(name: String){
        DEVELOPER("Dev"),
        RELEASE("Release"),
        Bate("Bate")
    }

    fun getHostUrl() =
        when(com.mh.universalscroe.BuildConfig.Env){
            Env.Bate.name -> hostUrlForBate
            Env.RELEASE.name -> hostUrlForRelease
            else -> hostUrlForDeveloper
        }

    class HttpQuery{
        class Status{
            companion object{
                const val STATUS_CODE = "statusCode"
                const val MESSAGE = "message"
                const val PRELOAD = "payload"
            }
        }
    }
}