package com.example.andika_3tia.Message

data class MessageModel(
    val senderName: String,   // Judul Quest (misal: "Lari 3 km")
    val messageText: String,  // Deskripsi Quest (misal: "Tanpa berhenti, target 25 menit")
    val avatarUrl: String,    // URL Gambar/Ikon Quest
    val time: String          // Badge EXP / Target Waktu (Sekaligus menjawab Tantangan 5.3)
)