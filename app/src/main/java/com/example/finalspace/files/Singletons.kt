package com.example.finalspace.files

import com.example.finalspace.files.api.FSApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object{
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        var httpClient = OkHttpClient.Builder()


        val FSApi = Retrofit.Builder()
            .baseUrl("https://finalspaceapi.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
            .create(FSApi::class.java)
    }
}