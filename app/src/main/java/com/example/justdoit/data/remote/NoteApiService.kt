package com.example.justdoit.data.remote

import com.example.justdoit.domain.model.NoteDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NoteApiService {
    // GET -> Récupérer toutes les notes
    @GET("demo/notes")
    suspend fun getNotes(): List<NoteDto>

    // GET -> récupérer une note par son id
    @GET("demo/notes/{id}")
    suspend fun getNoteById(@Path("id") id: Int): NoteDto
}