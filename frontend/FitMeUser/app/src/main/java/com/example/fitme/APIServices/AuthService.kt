package com.example.fitme.APIServices

import com.example.fitme.Entity.UserRecord
import com.example.fitme.EntityDao.AuthPassEmail
import com.example.fitme.EntityDao.AuthSignUp
import com.example.fitme.EntityDao.AuthSignUpRes
import com.example.fitme.EntityDao.SendLK
import com.example.fitme.EntityDao.SendLkRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/collections/users/auth-with-password")
    fun getUserAuth(
//        @Path(value = "id") id:String,
//        @Header("Authorization") authorization:String,
        @Body authPassEmail: AuthPassEmail
    ): Call<UserRecord>

    @POST("/api/collections/users/records")
    fun createUserAuth(
        @Body authSignUp: AuthSignUp
    ):Call<AuthSignUpRes>

    @POST("/api/v3/sms/send")
    fun sendUserOtpMessage(
        @Header("Authorization") authorization:String,
        @Body sendLK: SendLK
    ):Call<SendLkRes>
}