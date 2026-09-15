package com.example.registrodetarefas

data class Task(
    val name: String,
    val description: String,
    var isDone: Boolean = false
)
