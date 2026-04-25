package com.prog7311.c.repository

import com.prog7311.c.data.dao.UserDao
import com.prog7311.c.data.entity.User
import java.security.MessageDigest

class UserRepository(private val userDao: UserDao) {

    // Hash the password before storing — never store plain text
    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    // Register a new user
    // Returns true if successful, false if username already taken
    suspend fun register(username: String, password: String): Boolean {
        return try {
            val user = User(
                username = username,
                passwordHash = hashPassword(password)
            )
            userDao.insertUser(user)
            true
        } catch (e: Exception) {
            false
        }
    }

    // Login — returns the User if credentials are correct, null if not
    suspend fun login(username: String, password: String): User? {
        val user = userDao.getUserByUsername(username) ?: return null
        val hashedInput = hashPassword(password)
        return if (hashedInput == user.passwordHash) user else null
    }

    // Switch active profile
    suspend fun setActiveProfile(userId: Int, profileId: Int) {
        userDao.setActiveProfile(userId, profileId)
    }
}