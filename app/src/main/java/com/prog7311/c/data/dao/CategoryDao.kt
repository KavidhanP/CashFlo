package com.prog7311.c.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prog7311.c.data.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(category: Category): Long

    @Query("SELECT * FROM categories WHERE profileId = :profileId")
    fun getCategoriesForProfile(profileId: Int): Flow<List<Category>>

    @Query("DELETE FROM categories WHERE categoryId = :categoryId")
    suspend fun deleteCategory(categoryId: Int)
}