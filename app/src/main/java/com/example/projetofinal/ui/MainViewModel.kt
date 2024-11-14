package com.example.projetofinal.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetofinal.db.AppDatabase
import com.example.projetofinal.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var database: AppDatabase

    val users: MutableLiveData<List<User>> = MutableLiveData()

    fun initDb(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            database = AppDatabase.getDatabase(context)
            listUser()
        }
    }

    private fun listUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val listUsers = database.userDao().getAll()
            users.postValue(listUsers)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().insertAll(user)
            listUser()
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().updateUser(user)
            listUser()
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            database.userDao().delete(user)
            listUser()
        }
    }
}