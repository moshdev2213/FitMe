package com.example.fitme.APIServices

import com.example.fitme.EntityDao.MealRes
import com.example.fitme.EntityDao.WrkOutRes
import retrofit2.Call
import retrofit2.http.GET

interface MealService {
    @GET("/api/collections/meal/records")
    fun getAllMeals(

    ): Call<MealRes>
}