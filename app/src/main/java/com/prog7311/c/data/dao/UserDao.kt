package com.prog7311.c.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prog7311.c.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND passwordHash = :passwordHash LIMIT 1")
    suspend fun login(username: String, passwordHash: String): User?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): User?

    @Query("UPDATE users SET activeProfileId = :profileId WHERE userId = :userId")
    suspend fun setActiveProfile(userId: Int, profileId: Int)

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>
}