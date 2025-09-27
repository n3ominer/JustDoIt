package com.example.justdoit.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

/**
 * Écran des settings avec quelques sections (Account, Appearance, General, About).
 * On propose un bouton logout stylisé et des lignes de réglages.
 */
@Composable
fun SettingsScreen(onBack: () -> Unit, viewModel: NotesViewModel) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(title = "Settings", showBack = true, onBack = onBack)

            Text(text = "Vedang's Workspace", modifier = Modifier.padding(start = 18.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Liste de sections
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                SettingsRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Settings Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    title = "Account",
                    subtitle = "8 Items"
                )
                SettingsRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Settings Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    title = "Appearance",
                    subtitle = "5 Items"
                )
                SettingsRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Settings Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    title = "General",
                    subtitle = "12 Items"
                )
                SettingsRow(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Settings Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    title = "About",
                    subtitle = "2 Items"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(12.dp))

            // Bouton Logout stylisé
            Button(
                onClick = { /* logout */ },
                modifier = Modifier
                    .padding(horizontal = 18.dp,)
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD6D6)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Log Out",
                    color = Color.Red,
                    style = TextStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsRow(
    icon: @Composable () -> Unit, // Paramètre pour une icône dynamique
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) { // Aligner l'icône et le texte verticalement
                icon() // Affiche l'icône passée dynamiquement
                Column(
                    modifier = Modifier.padding(start = 8.dp) // Espacement entre l'icône et le texte
                ) {
                    Text(title, style = MaterialTheme.typography.titleMedium)
                    Text(subtitle, style = MaterialTheme.typography.titleSmall)
                }
            }
            Text(">", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SettingsScreenPreview() {

    SettingsScreen(onBack = {}, viewModel = NotesViewModel())
}