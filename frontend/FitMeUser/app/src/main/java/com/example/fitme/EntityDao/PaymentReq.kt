package com.example.fitme.EntityDao

data class PaymentReq(
    val cardCvc: Int,
    val cardExpire: String,
    val cardName: String,
    val cardNumber: String,
    val forValue: String,
    val from: String,
    val mealId: String,
    val total: Int,
    val trainerId: String
)
