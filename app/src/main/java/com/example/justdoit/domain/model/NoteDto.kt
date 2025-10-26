package com.example.justdoit.domain.model

import java.time.Instant

data class NoteDto(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: Long = Instant.now().epochSecond,
    val colorIndex: Int = 0
)