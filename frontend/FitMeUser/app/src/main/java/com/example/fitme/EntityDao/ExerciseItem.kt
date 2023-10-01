package com.example.fitme.EntityDao

import java.io.Serializable

data class ExerciseItem(
    val bodyPart: String,
    val calories: Int,
    val carbs: Int,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val description: String,
    val duration: String,
    val equipment: String,
    val fat: Int,
    val gifUrl: String,
    val id: String,
    val muscle: String,
    val name: String,
    val target: String,
    val updated: String
):Serializable
