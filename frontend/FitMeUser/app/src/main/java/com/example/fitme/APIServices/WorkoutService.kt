package com.example.fitme.APIServices

import com.example.fitme.EntityDao.WrkOutRes
import retrofit2.Call
import retrofit2.http.GET

interface WorkoutService {
    @GET("/api/collections/workout/records")
    fun getAllWorkouts(
    ): Call<WrkOutRes>
}