package com.example.fitme.EntityDao

data class PredictionResponse(
    val id: String,
    val project: String,
    val iteration: String,
    val created: String,
    val predictions: List<Prediction>
)
data class Prediction(
    val probability: Double,
    val tagId: String,
    val tagName: String
)