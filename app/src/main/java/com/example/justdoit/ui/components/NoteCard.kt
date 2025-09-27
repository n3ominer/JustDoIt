package com.example.justdoit.ui.components

import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.justdoit.data.Note

/**
 * Carte de note utilisée sur l'écran d'accueil.
 * colorIndex permet de varier l'arrière-plan comme sur le design.
 */
@Composable
fun NoteCard(note: Note, onClick: () -> Unit) {
    val bg = when (note.colorIndex % 3) {
        0 -> Color(0xFFDCCBFF) // violet clair
        1 -> Color(0xFFD9CBEF) // autre nuance
        else -> Color(0xFFE9D9F7)
    }

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .size(width = 160.dp, height = 120.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = bg,
        shadowElevation = 0.dp
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = note.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
            Text(text = note.content.take(80) + if (note.content.length > 80) "..." else "", fontSize = 12.sp, color = Color.Black)
        }
    }
}

@Composable
@Preview
fun NoteCardPreview() {
    NoteCard(
        note = Note(
            id = 1,
            title = "Sample Note",
            content = "This is a sample note content to demonstrate the NoteCard composable in Jetpack Compose.",
            colorIndex = 0
        ),
        onClick = {}
    )
}