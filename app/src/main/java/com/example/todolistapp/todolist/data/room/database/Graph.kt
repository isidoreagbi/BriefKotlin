package com.example.todolistapp.todolist.data.room.database

import android.content.Context
import com.example.todolistapp.todolist.data.room.repositories.UserRepository
import com.example.todolistapp.todolist.data.room.repositories.OTPCodeRepository

object Graph {

    private lateinit var todoListDatabase: TodoListDatabase
        private set

    val userRepository by lazy {
        UserRepository(
            userDao = todoListDatabase.userDao()
        )
    }

    val otpCodeRepository by lazy {
        OTPCodeRepository(
            otpCodeDao = todoListDatabase.otpCodeDao()
        )
    }

    fun provide(context: Context) {

        todoListDatabase = TodoListDatabase.getDatabase(context = context)
    }
}