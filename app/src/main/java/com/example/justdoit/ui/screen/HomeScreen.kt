package com.example.justdoit.ui.screen

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justdoit.NotesViewModel
import com.example.justdoit.ui.components.TopBar
import com.example.justdoit.ui.components.SearchBar
import com.example.justdoit.ui.components.NoteCard
import com.example.justdoit.ui.components.FloatingBottomBar
import com.example.justdoit.ui.theme.JustDoItTheme

/**
 * Écran principal affichant la grille de notes, la recherche et le bottom bar flottant.
 */
@Composable
fun HomeScreen(
    viewModel: NotesViewModel,
    onOpenNote: (Int) -> Unit,
    onOpenSettings: () -> Unit
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            TopBar(title = "Jus Do It !", showBack = false, onMore = onOpenSettings)

            Text(
                text = "${viewModel.notes.size} Notes",
                modifier = Modifier.padding(start = 18.dp, top = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth() // Le Row prend toute la largeur disponible
                    .padding(horizontal = 24.dp)
            ) {
                SearchBar(
                    modifier = Modifier.weight(1f), // La SearchBar prend tout l'espace restant
                    query = viewModel.query,
                    onSearch = { viewModel.updateQuery(it) }
                )
                IconButton(
                    onClick = { /* Action de tri */ },
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.List, // Icône de tri
                        contentDescription = "Sort",
                        tint = MaterialTheme.colorScheme.surfaceTint // Couleur personnalisée
                    )
                }
            }

            Text(
                text = "My Notes",
                modifier = Modifier.padding(start = 18.dp, top = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Grille de notes
            val notes = viewModel.filteredNotes()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 90.dp)
            ) {
                items(notes) { note ->
                    NoteCard(note = note, onClick = { onOpenNote(note.id) })
                }
            }

            // Floating bottom bar (déclenche l'ajout via le ViewModel)
            FloatingBottomBar(
                onHome = { /* Refresh les notes depuis un webservice*/ },
                onAddClicked = { viewModel.addSampleNote() },
                onProfile = { onOpenSettings() }
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun HomeScreenPreview() {
    // Note: Pour le preview, on utilise un ViewModel factice
    val fakeViewModel = NotesViewModel().apply {
        // Ajouter quelques notes factices pour le preview
        repeat(5) { addSampleNote() }
    }
    JustDoItTheme {
        Surface {
            HomeScreen(viewModel = fakeViewModel, onOpenNote = {}, onOpenSettings = {})
        }
    }
}