package com.example.justdoit.domain.usecase

/**
 * Aggregation of all note-related use cases.
 * Construct this in the composition root (e.g., `MainActivity`) and pass to ViewModels.
 */
data class NoteUseCases(
	val getAllNotes: GetAllNotesUseCase,
	val getNoteById: GetNoteByIdUseCase,
	val addNote: AddNoteUseCase,
	val deleteNoteUseCase: DeleteNoteUseCase,
	val updateNoteUseCase: UpdateNoteUseCase
)

