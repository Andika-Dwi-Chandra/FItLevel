package com.example.andika_3tia.Message

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val time: String,
    val category: String = "Harian" // Kategori untuk filter ChipGroup: Harian, Mingguan, Spesial
)