package com.example.fitme.EntityDao

data class PayResAll(
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int,
    val items: List<PaymentRes>
)
