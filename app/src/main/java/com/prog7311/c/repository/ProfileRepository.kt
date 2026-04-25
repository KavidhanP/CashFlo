package com.prog7311.c.repository


import com.prog7311.c.data.dao.ProfileDao
import com.prog7311.c.data.entity.Profile
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val profileDao: ProfileDao) {

    suspend fun createProfile(userId: Int, name: String, description: String = ""): Long {
        val profile = Profile(
            userId = userId,
            name = name,
            description = description
        )
        return profileDao.insertProfile(profile)
    }

    fun getProfilesForUser(userId: Int): Flow<List<Profile>> {
        return profileDao.getProfilesForUser(userId)
    }

    suspend fun deleteProfile(profileId: Int) {
        profileDao.deleteProfile(profileId)
    }
}