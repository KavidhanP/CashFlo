package com.prog7311.c.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "entries",
    foreignKeys = [
        ForeignKey(
            entity = Profile::class,
            parentColumns = ["profileId"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ],
    indices = [Index(value = ["profileId"])]
)
data class Entry(

    @PrimaryKey(autoGenerate = true)
    val entryId: Int = 0,

    val profileId: Int,

    val categoryId: Int? = null,

    val description: String,

    val amount: Double,


    val date: Long,

    val startTime: String,

    val endTime: String,

    // File path to photo on device, null if no photo
    val photoPath: String? = null
)