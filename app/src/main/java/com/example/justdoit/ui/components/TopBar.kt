package com.example.justdoit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * TopBar réutilisable:
 * - si showBack true -> affiche le bouton back
 * - sinon affiche juste le titre et un bouton "more"
 */
@Composable
fun TopBar(title: String, showBack: Boolean = false, onBack: () -> Unit = {}, onMore: () -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxWidth(), tonalElevation = 0.dp, shadowElevation = 0.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBack) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            IconButton(onClick = onMore) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
        }
    }
}

@Composable
@Preview
fun TopBarPreview() {
    Column {
        TopBar(title = "Title")
        TopBar(title = "Title", showBack = true)
    }
}