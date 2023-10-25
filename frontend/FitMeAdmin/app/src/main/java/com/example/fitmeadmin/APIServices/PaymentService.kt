package com.example.fitmeadmin.APIServices

import com.example.fitmeadmin.EntityDao.Addwork
import com.example.fitmeadmin.EntityDao.ExerciseItem
import com.example.fitmeadmin.EntityDao.PaymentItem
import com.example.fitmeadmin.EntityDao.PaymentRes
import com.example.fitmeadmin.EntityDao.WrkOutRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentService {
    @GET("/api/collections/payment/records")
    fun getAllPayment(
    ): Call<PaymentRes>

    @PATCH("/api/collections/payment/records/{id}")
    fun updatePayment(
        @Path("id") id: String, // Replace with the data type of your 'id' parameter
        @Body updatePayment: PaymentItem // Replace with the data type of your updated workout data
    ): Call<PaymentItem>


//    @POST("/api/collections/payment/records")
//    fun addWorkout(@Body newWorkout: Addwork): Call<Addwork>
}