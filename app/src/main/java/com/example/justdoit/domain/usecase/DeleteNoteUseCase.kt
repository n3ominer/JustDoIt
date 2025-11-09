package com.example.justdoit.domain.usecase

import com.example.justdoit.domain.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {

    operator fun invoke(noteId: Int) {
        repository.deleteNote(noteId)
    }
}