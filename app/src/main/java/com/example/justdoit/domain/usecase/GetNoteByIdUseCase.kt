package com.example.justdoit.domain.usecase

import com.example.justdoit.data.Note
import com.example.justdoit.domain.repository.NoteRepository


class GetNoteByIdUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note? = repository.getById(id)
}
