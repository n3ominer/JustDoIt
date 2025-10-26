package com.example.justdoit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.justdoit.data.repository.NoteRepository
import com.example.justdoit.ui.navigation.NavGraph
import com.example.justdoit.ui.theme.JustDoItTheme
import com.example.justdoit.ui.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {

    // Instanciation du ViewModel (scoped à l'activité)
    val notesRepo = NoteRepository()
    val vm: NotesViewModel = NotesViewModel(repository = notesRepo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustDoItTheme {
                // Lancement du Graph de navigation en lui passant le ViewModel
                NavGraph(viewModel = vm)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustDoItTheme {
        val vm: NotesViewModel = viewModel()
        NavGraph(viewModel = vm)
    }
}