package com.example.justdoit.domain.usecase

import com.example.justdoit.data.Note
import com.example.justdoit.domain.repository.NoteRepository

class AddNoteUseCase(private val repository: NoteRepository) {

    operator fun invoke(note: Note) {
        repository.addNote(note)
    }
}