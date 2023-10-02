package com.example.fitme.APIServices

import com.example.fitme.EntityDao.PayResAll
import com.example.fitme.EntityDao.PaymentReq
import com.example.fitme.EntityDao.PaymentRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PaymentService {
    @POST("/api/collections/payment/records")
    fun insertPayment(
        @Body paymentReq: PaymentReq
    ):Call<PaymentRes>

    @GET("/api/collections/payment/records")
    fun getAllPayments():Call<PayResAll>
}