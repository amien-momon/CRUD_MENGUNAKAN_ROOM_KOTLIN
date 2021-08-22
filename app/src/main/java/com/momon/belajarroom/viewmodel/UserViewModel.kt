package com.momon.belajarroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.momon.belajarroom.model.user
import com.momon.belajarroom.data.userDB
import com.momon.belajarroom.repository.userRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<user>>
    private  val repository: userRepository
    init {
        val userDao = userDB.getDatabase(application).userDao()
        repository = userRepository(userDao)
        readAllData = repository.readALLData
    }

    fun addUser(user: user){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun updateUser(user: user){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun deleteUser(user: user){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

}