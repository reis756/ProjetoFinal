package com.example.projetofinal.model

import java.util.Calendar

data class Contact(
    val nome: String,
    val foto: String,
    val mensagens: List<Message>
)

data class Message(
    val texto: String,
    val dataHora: String,
    val data: Calendar
)
