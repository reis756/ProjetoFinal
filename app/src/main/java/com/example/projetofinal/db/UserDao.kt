package com.example.projetofinal.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projetofinal.model.User

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg users: User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE name LIKE :name AND " +
            "age LIKE :age LIMIT 1")
    fun findByName(name: String, age: String): User
}