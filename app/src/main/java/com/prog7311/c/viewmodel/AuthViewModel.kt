package com.prog7311.c.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prog7311.c.data.entity.User
import com.prog7311.c.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    // The currently logged in user — null means nobody is logged in
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    // true = success, false = username already taken
    private val _registerResult = MutableStateFlow<Boolean?>(null)
    val registerResult: StateFlow<Boolean?> = _registerResult

    // true = success, false = wrong username/password
    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    fun register(username: String, password: String) {
        viewModelScope.launch {
            val success = userRepository.register(username, password)
            _registerResult.value = success
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.login(username, password)
            _currentUser.value = user
            _loginResult.value = user != null
        }
    }

    fun logout() {
        _currentUser.value = null
    }

    fun setActiveProfile(userId: Int, profileId: Int) {
        viewModelScope.launch {
            userRepository.setActiveProfile(userId, profileId)
        }
    }
}