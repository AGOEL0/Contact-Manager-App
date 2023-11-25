package com.example.contactmanager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.contactmanager.room.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

  @Insert
  suspend fun insertUser(user: User): Long

  @Update
  suspend fun updateUser(user: User): Int

  @Delete
  suspend fun deleteUser(user: User): Int

  @Query("DELETE FROM user")
  suspend fun deleteAll()

  @Query("SELECT * FROM user")
  fun getAllUserInDb():LiveData<List<User>>
}
