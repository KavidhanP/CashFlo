package com.prog7311.c.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prog7311.c.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories WHERE profileId = :profileId ORDER BY name ASC")
    fun getCategoriesForProfile(profileId: Int): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE profileId = :profileId ORDER BY name ASC")
    suspend fun getCategoriesForProfileOnce(profileId: Int): List<Category>

    @Query("SELECT * FROM categories WHERE categoryId = :categoryId LIMIT 1")
    suspend fun getCategoryById(categoryId: Int): Category?

    @Query("DELETE FROM categories WHERE profileId = :profileId")
    suspend fun deleteAllCategoriesForProfile(profileId: Int)
}