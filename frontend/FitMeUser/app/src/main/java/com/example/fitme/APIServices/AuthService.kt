package com.example.fitme.APIServices

import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.AuthPassEmail
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("/api/collections/users/auth-with-password")
    fun getUserAuth(
//        @Path(value = "id") id:String,
//        @Header("Authorization") authorization:String,
        @Body authPassEmail: AuthPassEmail
    ): Call<UserRecord>
}