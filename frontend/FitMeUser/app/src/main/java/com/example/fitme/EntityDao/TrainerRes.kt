package com.example.fitme.EntityDao

import java.io.Serializable

data class TrainerRes(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<TrainerItem>
):Serializable

data class TrainerItem(
    val age: Int,
    val availability: String,
    val charges: Int,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val description: String,
    val email: String,
    val experience: Int,
    val gender: String,
    val id: String,
    val location: String,
    val name: String,
    val profile: String,
    val specialized: String,
    val updated: String
):Serializable
