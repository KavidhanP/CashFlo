package com.prog7311.c.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    foreignKeys = [
        ForeignKey(
            entity = Profile::class,
            parentColumns = ["profileId"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.Companion.CASCADE  // delete profile → delete its categories
        )
    ],
    indices = [Index(value = ["profileId"])]
)
data class Category(

    @PrimaryKey(autoGenerate = true)
    val categoryId: Int = 0,

    // Which profile this category belongs to
    val profileId: Int,

    // "Food", "Transport", "Entertainment"
    val name: String,

)