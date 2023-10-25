package com.example.fitmeadmin.EntityDao

import java.io.Serializable

data class UserItem(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val username: String,
    val verified: Boolean,
    val emailVisibility: Boolean,
    val email: String,
    val created: String,
    val updated: String,
    val name: String,
    val avatar: String,
    val description: String,
    val telephone: String

): Serializable
