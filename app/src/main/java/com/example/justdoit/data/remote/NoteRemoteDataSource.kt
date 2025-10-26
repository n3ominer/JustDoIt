package com.example.justdoit.data.remote

import com.example.justdoit.network.RetrofitInstance

class NoteRemoteDataSource {

    private val api = RetrofitInstance.api

    suspend fun fetchNotes() = api.getNotes()

    suspend fun fetchNoteDetails(id: Int) = api.getNoteById(id)
}
