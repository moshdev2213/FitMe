package com.example.fitme.EntityDao

data class PaymentRes(
    val cardCvc: Int,
    val cardExpire: String,
    val cardName: String,
    val cardNumber: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val forValue: String,
    val from: String,
    val id: String,
    val mealId: String,
    val total: Int,
    val trainerId: String,
    val updated: String
)
