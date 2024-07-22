package com.example.todolistapp.todolist.data.room.repositories

import com.example.todolistapp.todolist.data.room.models.OTPCode
import com.example.todolistapp.todolist.data.room.daos.OTPCodeDao
import kotlinx.coroutines.flow.Flow

class OTPCodeRepository(
    private val otpCodeDao: OTPCodeDao
){
    suspend fun insert(optCode: OTPCode){otpCodeDao.insert(optCode)}
    fun checkOtpCode(email: String,code: String): Flow<List<OTPCode>> =otpCodeDao.checkOtpCode(email,code)

}