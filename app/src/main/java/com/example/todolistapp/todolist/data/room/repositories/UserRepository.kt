package com.example.todolistapp.todolist.data.room.repositories

import com.example.todolist.data.room.daos.UserDao
import com.example.todolistapp.todolist.data.room.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {
    val userList = userDao.usersList()
    suspend fun  register(user: User) {userDao.register(user)}
    fun getUserByEmail(email: String): Flow<List<User>> = userDao.getUserByEmail(email)

    suspend fun activateUserByEmail(email: String) =userDao.activateUserByEmail(email)
}