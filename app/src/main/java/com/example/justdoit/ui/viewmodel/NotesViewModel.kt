package com.example.justdoit.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justdoit.data.Note
import com.example.justdoit.data.repository.NoteRepository
import com.example.justdoit.domain.model.NoteDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * ViewModel simple exposant la liste des notes et quelques actions.
 * On utilise des State pour que Compose observe les changements automatiquement.
 */
class NotesViewModel(
    private val repository: NoteRepository = NoteRepository()
) : ViewModel() {

    // Liste observable
    private val _notes = mutableStateListOf<Note>().apply { addAll(repository.getAll()) }
    val notes: List<Note> get() = _notes

    // Texte de recherche
    var query by mutableStateOf("")
        private set




    // ==========================================
    // ========== REMOTE CRUD OPERATIONS ========
    // ==========================================
    private val _remotenotes = MutableStateFlow<List<Note>>(emptyList())
    val remotenotes = _remotenotes.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        fetchNotes() // 🔄 Chargement automatique au démarrage
    }

    fun fetchNotes() {
        viewModelScope.launch {
            try {
                _remotenotes.value = repository.getNotes().map { mapNoteDtoToNote(it) }
            } catch (e: Exception) {
                _error.value = "Erreur de chargement: ${e.message}"
            }
        }
    }

    private fun mapNoteDtoToNote(dto: NoteDto) : Note {
        return Note(
                id = dto.id,
                title = dto.title,
                content = dto.content
            )
    }



    // ========================================
    // ========== MOCK CRUD OPERATIONS ========
    // ========================================
    fun updateQuery(q: String) { query = q }

    // Ajouter une note factice (simule le +)
    fun addSampleNote() {
        val id = (Random.Default.nextInt(1000) + _notes.size + 1)
        val note = Note(
            id = id,
            title = "New Note #$id",
            content = "Contenu de la note $id. Ajoute du texte ici.",
            colorIndex = Random.Default.nextInt(0, 3)
        )
        repository.add(note)
        _notes.add(0, note)
    }

    fun getNoteById(id: Int): Note? = _remotenotes.value.firstOrNull { it.id == id }

    fun deleteNote(id: Int) {
        repository.delete(id)
        _notes.removeAll { it.id == id }
    }

    fun updateNote(note: Note) {
        repository.update(note)
        val idx = _notes.indexOfFirst { it.id == note.id }
        if (idx >= 0) _notes[idx] = note
    }

    // Liste filtrée par la query
    fun filteredNotes(): List<Note> {
        val q = query.trim()
        if (q.isEmpty()) return notes
        return notes.filter { it.title.contains(q, ignoreCase = true) || it.content.contains(q, ignoreCase = true) }
    }
}