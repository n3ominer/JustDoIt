package com.example.justdoit.network


import com.example.justdoit.data.remote.NoteApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://my-json-server.typicode.com/RamzyK/"

    val api: NoteApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convert JSON → Objet Kotlin
            .build()
            .create(NoteApiService::class.java)
    }
}
