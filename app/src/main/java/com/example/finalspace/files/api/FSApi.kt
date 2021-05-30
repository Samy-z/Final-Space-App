package com.example.finalspace.files.api

import com.example.finalspace.files.list.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FSApi {
    @GET("/api/v0/character")
    fun getCharacterList(): Call<List<Character>>

    @GET("/api/v0/character/{id}")
    fun getCharacterDetail(@Path("id") id: String): Call<Character>
}