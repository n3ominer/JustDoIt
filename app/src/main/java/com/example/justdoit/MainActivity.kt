package com.example.justdoit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.justdoit.data.local.datastore.DataStoreManager
import com.example.justdoit.data.repository.NoteRepositoryImpl
import com.example.justdoit.domain.usecase.AddNoteUseCase
import com.example.justdoit.domain.usecase.DeleteNoteUseCase
import com.example.justdoit.domain.usecase.GetAllNotesUseCase
import com.example.justdoit.domain.usecase.GetNoteByIdUseCase
import com.example.justdoit.domain.usecase.NoteUseCases
import com.example.justdoit.domain.usecase.UpdateNoteUseCase
import com.example.justdoit.ui.navigation.NavGraph
import com.example.justdoit.ui.theme.JustDoItTheme
import com.example.justdoit.viewmodel.NotesViewModel
import com.example.justdoit.viewmodel.SessionViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val notesRepo = NoteRepositoryImpl()
    val vm: NotesViewModel = NotesViewModel(
        NoteUseCases(
            getAllNotes = GetAllNotesUseCase(notesRepo),
            getNoteById = GetNoteByIdUseCase(notesRepo),
            addNote = AddNoteUseCase(notesRepo),
            deleteNoteUseCase = DeleteNoteUseCase(notesRepo),
            updateNoteUseCase = UpdateNoteUseCase(notesRepo)
        )
    )

    private lateinit var dataStore: DataStoreManager
    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        dataStore = DataStoreManager(this)
        sessionViewModel = SessionViewModel(dataStore)

        initFakeData()

        setContent {
            JustDoItTheme {
                // Lancement du Graph de navigation en lui passant le ViewModel
                NavGraph(
                    viewModel = vm,
                    sessionVm = sessionViewModel
                )
            }
        }
    }

    fun initFakeData() {
        lifecycleScope.launch {
            dataStore.saveDarkMode(true)
        }
    }
}