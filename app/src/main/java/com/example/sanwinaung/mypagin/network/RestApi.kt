package com.example.sanwinaung.mypagin.network

import com.example.sanwinaung.mypagin.utils.Const
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {
    val builder = Retrofit.Builder()
            .baseUrl(Const.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())

    fun <S> createService(serviceClass:Class<S>) : S {
        val retrofit = RestApi.builder
                .build()
        return retrofit.create(serviceClass)
    }
}