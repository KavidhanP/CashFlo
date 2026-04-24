package com.prog7311.c

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "goals",
    foreignKeys = [
        ForeignKey(
            entity = Profile::class,
            parentColumns = ["profileId"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["profileId"])]
)
data class Goal(

    @PrimaryKey(autoGenerate = true)
    val goalId: Int = 0,

    val profileId: Int,

    // Minimum and maximum monthly spending targets
    val minAmount: Double,
    val maxAmount: Double,

    // Month and year this goal applies to e.g. month = 4, year = 2026
    val month: Int,
    val year: Int
)