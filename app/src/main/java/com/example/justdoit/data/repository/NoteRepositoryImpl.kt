package com.example.justdoit.data.repository

import com.example.justdoit.data.Note
import com.example.justdoit.data.local.db.NoteDao
import com.example.justdoit.data.local.db.NoteEntity
import com.example.justdoit.data.remote.NoteRemoteDataSource
import com.example.justdoit.domain.model.NoteDto
import com.example.justdoit.domain.repository.NoteRepository

/**
 * Repository factice. En production cela serait une base de données Room / remote API.
 * Ici on garde une liste en mémoire.
 */
class NoteRepositoryImpl(
    private val remote: NoteRemoteDataSource = NoteRemoteDataSource(),
    private val dao: NoteDao
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
        var remoteNotes: List<NoteDto> = listOf()
        try {
            remoteNotes = remote.fetchNotes()
        } catch (e: Exception) {
            // Handle exceptions such as network errors
            println("Error fetching notes: ${e.message}")
        }

        return remoteNotes
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
        if (remoteNotes.isEmpty()) {
            // Get data from local database if remote is empty
            val localNotes = dao.getAllNotes().map {
                NoteDto(
                    it.id,
                    it.title,
                    it.content
                )
            }

            return localNotes + notes.map {
                NoteDto(
                    it.id,
                    it.title,
                    it.content
                )
            }
        }

        val completeListNote = (notes + remoteNotes).map {
            NoteDto(
                it.id,
                it.title,
                it.content
            )
        }

        completeListNote.forEach {
            val noteEntity = NoteEntity(
                id = it.id,
                title = it.title,
                content = it.content,
                date = System.currentTimeMillis()
            )
            dao.insertNote(noteEntity)
        }

        return completeListNote
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