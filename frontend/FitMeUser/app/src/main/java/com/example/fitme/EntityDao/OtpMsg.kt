package com.example.fitme.EntityDao

class OtpMsg(randomNumber:String) {
    private val message = "Dear Sir/Madame,\n\nYour One-Time Password for your Login attempt at FitME is $randomNumber. Please use this password within the next 10 minutes to complete your online Login on your FitMe App.\n\nFor more Information\nPlease contact our 24-hour Call Centre through +94 (0) 11 2253252.\n\nBest regards,\nFitMe Pvt.Ltd\n"

    fun getMsg():String{
        return this.message
    }
}