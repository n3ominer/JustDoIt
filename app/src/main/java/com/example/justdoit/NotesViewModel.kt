package com.example.justdoit

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.justdoit.data.Note
import kotlin.random.Random

/**
 * ViewModel simple exposant la liste des notes et quelques actions.
 * On utilise des State pour que Compose observe les changements automatiquement.
 */
class NotesViewModel : ViewModel() {
    private val repo = NoteRepository()

    // Liste observable
    private val _notes = mutableStateListOf<Note>().apply { addAll(repo.getAll()) }
    val notes: List<Note> get() = _notes

    // Texte de recherche
    var query by mutableStateOf("")
        private set


    fun updateQuery(q: String) { query = q }

    // Ajouter une note factice (simule le +)
    fun addSampleNote() {
        val id = (Random.nextInt(1000) + _notes.size + 1)
        val note = Note(
            id = id,
            title = "New Note #$id",
            content = "Contenu de la note $id. Ajoute du texte ici.",
            colorIndex = Random.nextInt(0, 3)
        )
        repo.add(note)
        _notes.add(0, note)
    }

    fun getNoteById(id: Int): Note? = repo.getById(id)

    fun deleteNote(id: Int) {
        repo.delete(id)
        _notes.removeAll { it.id == id }
    }

    fun updateNote(note: Note) {
        repo.update(note)
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