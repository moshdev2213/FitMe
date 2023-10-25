package com.example.fitmeadmin.EntityDao

import java.io.Serializable

data class PaymentItem(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val cardCvc: Int,
    val cardExpire: String,
    val cardName: String,
    val total: Int,
    val from: String,
    val forValue: String,
    val trainerId: String,
    val mealId: String,
    val cardNumber: String
):Serializable
