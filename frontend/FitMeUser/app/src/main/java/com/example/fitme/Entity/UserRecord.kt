package com.example.fitme.Entity

import java.io.Serializable

data class UserRecord(
    val token: String,
    val record: Record
):Serializable
data class Record(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val username: String,
    val description: String,
    val verified: Boolean,
    val emailVisibility: Boolean,
    val email: String,
    val created: String,
    val updated: String,
    val name: String,
    val telephone: String,
    val avatar: String
):Serializable