package com.prog7311.c.repository

import com.prog7311.c.data.dao.CategoryDao
import com.prog7311.c.data.entity.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {

    suspend fun createCategory(profileId: Int, name: String): Long {
        val category = Category(
            profileId = profileId,
            name = name,

        )
        return categoryDao.insertCategory(category)
    }

    fun getCategoriesForProfile(profileId: Int): Flow<List<Category>> {
        return categoryDao.getCategoriesForProfile(profileId)
    }

    suspend fun deleteCategory(categoryId: Int) {
        categoryDao.deleteCategory(categoryId)
    }
}