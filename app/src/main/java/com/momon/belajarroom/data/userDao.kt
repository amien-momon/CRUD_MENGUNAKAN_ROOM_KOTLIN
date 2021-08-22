package com.momon.belajarroom.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.momon.belajarroom.model.user

@Dao
interface userDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(vararg users: user)

    @Update
    suspend fun updateUser(user: user)

    @Delete
    suspend fun deleteUser(user: user)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readALLData(): LiveData<List<user>>

}
