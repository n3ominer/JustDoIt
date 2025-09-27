package com.example.justdoit.ui.screen

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.justdoit.ui.components.TopBar
import com.example.justdoit.NotesViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * Écran des settings avec quelques sections (Account, Appearance, General, About).
 * On propose un bouton logout stylisé et des lignes de réglages.
 */
@Composable
fun SettingsScreen(onBack: () -> Unit, viewModel: NotesViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = "Settings", showBack = true, onBack = onBack)

        Text(text = "Vedang's Workspace", modifier = Modifier.padding(start = 18.dp))

        Spacer(modifier = Modifier.height(12.dp))

        // Bouton Logout stylisé
        Button(
            onClick = { /* logout */ },
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD6D6)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Log Out", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Liste de sections
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            SettingsRow(title = "Account", subtitle = "8 Items")
            SettingsRow(title = "Appearance", subtitle = "5 Items")
            SettingsRow(title = "General", subtitle = "12 Items")
            SettingsRow(title = "About", subtitle = "2 Items")
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom bar identique
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            // On réutilise visuellement un simple bouton central
            Surface(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.9f)
                    .height(64.dp),
                color = Color(0xFFF3D9F0),
                shape = RoundedCornerShape(32.dp)
            ) {}
        }
    }
}

@Composable
private fun SettingsRow(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, style = MaterialTheme.typography.titleSmall)
            }


            Text(">", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen(onBack = {}, viewModel = NotesViewModel())
}