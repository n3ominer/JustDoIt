package com.example.justdoit.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.tooling.preview.Preview

/**
 * BottomBar flottant avec trois boutons: home, plus, profile.
 * onAddClicked déclenche l'ajout.
 */
@Composable
fun FloatingBottomBar(onHome: () -> Unit = {}, onAddClicked: () -> Unit = {}, onProfile: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Fond arrondi
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(64.dp)
                .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(32.dp))
                .background(Color(0xFFF3D9F0))
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onHome) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

            // Bouton + central
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8E6BD9)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onAddClicked, modifier = Modifier.size(56.dp)) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
                }
            }

            IconButton(onClick = onProfile) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
            }
        }
    }
}

@Composable
@Preview
fun FloatingBottomBarPreview() {
    FloatingBottomBar()
}