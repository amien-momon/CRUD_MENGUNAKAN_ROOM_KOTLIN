package com.momon.belajarroom.repository

import androidx.lifecycle.LiveData
import com.momon.belajarroom.model.user
import com.momon.belajarroom.data.userDao

class userRepository(private  val userDao: userDao) {

    val readALLData: LiveData<List<user>> = userDao.readALLData()

    suspend fun addUser(user: user){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: user){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: user){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()

    }
}