package com.example.justdoit.data.repository

import com.example.justdoit.data.Note
import com.example.justdoit.data.remote.NoteRemoteDataSource
import com.example.justdoit.domain.model.NoteDto

/**
 * Repository factice. En production cela serait une base de données Room / remote API.
 * Ici on garde une liste en mémoire.
 */
class NoteRepository(
    private val remote: NoteRemoteDataSource = NoteRemoteDataSource()
) {

    // données initiales
    private val notes = mutableListOf(
        Note(
            1,
            "Hello World!",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
            colorIndex = 0
        ),
        Note(
            2,
            "Work Meeting Notes",
            "Discussed progress on project X, deadlines, and...",
            colorIndex = 1
        ),
        Note(
            3,
            "Class Notes",
            "Lecture on Biology: DNA structure and replication...",
            colorIndex = 2
        ),
        Note(
            4,
            "Project Plan",
            "Research, design, implementation, testing, deployment",
            colorIndex = 1
        ),
        Note(
            5,
            "To-Do List",
            "Finish homework, call the dentist, buy groceries...",
            colorIndex = 2
        ),
    )


    // ==========================================
    // ========== REMOTE CRUD OPERATIONS ========
    // ==========================================
    suspend fun getNotes(): List<NoteDto> {
        return remote.fetchNotes()
    }

    suspend fun getNoteDetails(id: Int): NoteDto {
        return remote.fetchNoteDetails(id)
    }


    // ========================================
    // ========== MOCK CRUD OPERATIONS ========
    // ========================================
    fun getAll(): List<Note> = notes.toList()

    fun getById(id: Int): Note? = notes.find { it.id == id }

    fun add(note: Note) {
        notes.add(0, note) // ajoute en début
    }

    fun delete(id: Int) {
        notes.removeAll { it.id == id }
    }

    fun update(updated: Note) {
        val idx = notes.indexOfFirst { it.id == updated.id }
        if (idx >= 0) notes[idx] = updated
    }
}