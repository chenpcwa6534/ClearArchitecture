package com.mh.universalscroe.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mh.universalscroe.datacenter.Repository
import com.mh.universalscroe.datacenter.api.Contract
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiClientScope = module {
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(Contract().connectTimeout, TimeUnit.SECONDS)
            .build()
    }

    fun provideConverter(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Contract().getHostUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    fun provideRepository(client: Retrofit) =
        Repository(client)

    single { provideOkHttpClient() }
    single { provideConverter() }
    single { provideRetrofit(get(), get()) }
    single { provideRepository(get()) }
}