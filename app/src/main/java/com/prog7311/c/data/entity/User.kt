package com.prog7311.c.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class User(

    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,

    // The username they log in with — must be unique
    val username: String,

    // Never store plain passwords — store a hash
    // We'll handle the hashing in AuthRepository
    val passwordHash: String,

    val createdAt: Long = System.currentTimeMillis(),

    // Supports multiple profiles feature — tracks which profile is active
    val activeProfileId: Int? = null
)