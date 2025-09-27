package com.example.justdoit.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * Barre de recherche simple avec placeholder.
 * Appelle onSearch à chaque changement.
 */
@Composable
fun SearchBar(modifier: Modifier = Modifier, query: String, onSearch: (String) -> Unit) {
    Surface(
        modifier = modifier
            .height(54.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth() // Le Row interne prend toute la largeur allouée par le modifier
        ) {
            TextField(
                value = query,
                onValueChange = onSearch,
                placeholder = { Text("Search here...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth() // Le TextField remplit le Row interne
            )
        }
    }
}

@Composable
@Preview
fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SearchBar(query = query, onSearch = { query = it })
}