package com.example.justdoit.domain.usecase

import com.example.justdoit.domain.model.NoteDto
import com.example.justdoit.domain.repository.NoteRepository

class GetAllNotesUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(): List<NoteDto> = repository.getAllNotes()
}
