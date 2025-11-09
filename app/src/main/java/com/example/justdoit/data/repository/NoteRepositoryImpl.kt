package com.example.justdoit.data.repository

import com.example.justdoit.data.Note
import com.example.justdoit.data.remote.NoteRemoteDataSource
import com.example.justdoit.domain.model.NoteDto
import com.example.justdoit.domain.repository.NoteRepository

/**
 * Repository factice. En production cela serait une base de données Room / remote API.
 * Ici on garde une liste en mémoire.
 */
class NoteRepositoryImpl(
    private val remote: NoteRemoteDataSource = NoteRemoteDataSource()
): NoteRepository {

    // données initiales
    private val notes = mutableListOf(
        Note(
            1,
            "Local note #1!",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
            colorIndex = 0
        ),
        Note(
            2,
            "Local note #2!",
            "Discussed progress on project X, deadlines, and...",
            colorIndex = 1
        ),
        Note(
            3,
            "Local note #3!",
            "Lecture on Biology: DNA structure and replication...",
            colorIndex = 2
        ),
        Note(
            4,
            "Local note #4!",
            "Research, design, implementation, testing, deployment",
            colorIndex = 1
        ),
        Note(
            5,
            "Local note #5!",
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

    override suspend fun getAllNotes(): List<NoteDto> {
        val remoteNotes = getNotes().map {
            Note(
                it.id,
                it.title,
                it.content
            )
        }

        return (notes + remoteNotes).map {
            NoteDto(
                it.id,
                it.title,
                it.content
            )
        }
    }

    override suspend fun getById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override fun addNote(note: Note) {}

    override fun deleteNote(noteId: Int): Boolean {
        val noteToRemove = this.notes.find { it.id == noteId }
        if (noteToRemove != null) {
            this.notes.remove(noteToRemove)
            return true
        } else {
            return false
        }
    }

    override fun update(updateNote: Note) {
        val idx = notes.indexOfFirst { it.id == updateNote.id }
        if (idx >= 0) notes[idx] = updateNote
    }
}