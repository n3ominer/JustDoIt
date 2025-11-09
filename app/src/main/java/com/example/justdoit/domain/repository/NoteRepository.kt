package com.example.justdoit.domain.repository


import com.example.justdoit.data.Note
import com.example.justdoit.domain.model.NoteDto


/**
 * Domain-level repository interface for notes.
 * Define the contract here so the app depends on the interface (clean architecture).
 */
interface NoteRepository {
    suspend fun getAllNotes(): List<NoteDto>
    suspend fun getById(id: Int): Note?
    fun addNote(note: Note)
    fun deleteNote(note: Note): Boolean
    fun update(updateNote: Note)
}
