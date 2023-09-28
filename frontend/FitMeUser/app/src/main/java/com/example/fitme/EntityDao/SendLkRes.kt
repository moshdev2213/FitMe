package com.example.fitme.EntityDao

data class SendLkRes (
    val status: String,
    val message: String,
    val data: MessageData
    )

data class MessageData(
    val uid: String,
    val to: String,
    val from: String,
    val message: String,
    val status: String,
    val cost: String
)