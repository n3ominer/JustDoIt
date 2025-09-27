package com.example.justdoit.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.justdoit.NotesViewModel
import com.example.justdoit.ui.components.TopBar
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * Écran de détail d'une note.
 * Affiche le titre en grand, la date, un tag, et le contenu.
 */
@Composable
fun DetailScreen(noteId: Int, viewModel: NotesViewModel, onBack: () -> Unit) {
    val note = viewModel.getNoteById(noteId)
    if (note == null) {
        // simple fallback si note introuvable
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Note introuvable")
            Button(onClick = onBack) {
                Text("Retour")
            }
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = "", showBack = true, onBack = onBack)
        Spacer(modifier = Modifier.height(6.dp))

        // Date
        Text(
            text = "20th Feb 2023 11:53 pm", // statique pour le design, sinon formatter createdAt
            modifier = Modifier.padding(start = 18.dp),
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Grand titre (typographie oversize comme sur le design)
        Text(
            text = note.title,
            modifier = Modifier.padding(start = 18.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tag "My Notes" stylisé
        Surface(
            modifier = Modifier.padding(start = 18.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
            color = Color(0xFFF3D9F0)
        ) {
            Text(text = "  My Notes  ", modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Contenu
        Text(
            text = note.content,
            modifier = Modifier.padding(horizontal = 18.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        // BottomBar simple pour cohérence
        FloatingActionButton(onClick = {}) {
            Text("+")
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun DetailScreenPreview() {
    // Note d'exemple pour le preview
    val sampleNote = com.example.justdoit.data.Note(
        id = 1,
        title = "Sample Note Title",
        content = "This is the content of the sample note. It can be quite long and detailed.",
        createdAt = System.currentTimeMillis()
    )
    val viewModel = NotesViewModel().apply {
        // Ajouter la note d'exemple au ViewModel
        addSampleNote()
    }
    DetailScreen(noteId = 1, viewModel = viewModel, onBack = {})
}