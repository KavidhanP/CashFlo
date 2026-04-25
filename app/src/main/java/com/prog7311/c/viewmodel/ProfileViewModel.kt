package com.prog7311.c.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prog7311.c.data.entity.Profile
import com.prog7311.c.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    fun getProfilesForUser(userId: Int): Flow<List<Profile>> {
        return profileRepository.getProfilesForUser(userId)
    }

    fun createProfile(userId: Int, name: String, description: String = "") {
        viewModelScope.launch {
            profileRepository.createProfile(userId, name, description)
        }
    }

    fun deleteProfile(profileId: Int) {
        viewModelScope.launch {
            profileRepository.deleteProfile(profileId)
        }
    }
}