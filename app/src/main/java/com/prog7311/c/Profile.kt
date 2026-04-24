package com.prog7311.c

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "profiles",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class Profile(

    @PrimaryKey(autoGenerate = true)
    val profileId: Int = 0,

    val userId: Int,

    // e.g. "Personal", "Business", "Holiday Trip"
    val name: String,

    val description: String = "",

    val createdAt: Long = System.currentTimeMillis()
)