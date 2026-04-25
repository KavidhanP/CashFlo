package com.prog7311.c.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prog7311.c.data.entity.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert
    suspend fun insertProfile(profile: Profile): Long

    @Query("SELECT * FROM profiles WHERE userId = :userId")
    fun getProfilesForUser(userId: Int): Flow<List<Profile>>

    @Query("DELETE FROM profiles WHERE profileId = :profileId")
    suspend fun deleteProfile(profileId: Int)
}