package com.example.justdoit.data

import java.time.Instant

/**
 * Modèle simple d'une note / todo.
 * id: identifiant unique
 * title: titre
 * content: texte complet
 * createdAt: timestamp
 * colorIndex: int pour varier la couleur d'affichage
 */
data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: Long = Instant.now().epochSecond,
    val colorIndex: Int = 0
)