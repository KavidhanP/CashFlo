package com.prog7311.c.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prog7311.c.Profile

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile): Long

    @Update
    suspend fun updateProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

    @Query("SELECT * FROM profiles WHERE userId = :userId ORDER BY createdAt ASC")
    fun getProfilesForUser(userId: Int): LiveData<List<Profile>>

    @Query("SELECT * FROM profiles WHERE userId = :userId ORDER BY createdAt ASC")
    suspend fun getProfilesForUserOnce(userId: Int): List<Profile>

    @Query("SELECT * FROM profiles WHERE profileId = :profileId LIMIT 1")
    suspend fun getProfileById(profileId: Int): Profile?

    @Query("DELETE FROM profiles WHERE profileId = :profileId")
    suspend fun deleteProfileById(profileId: Int)
}