package com.prog7311.c.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prog7311.c.data.entity.Category
import com.prog7311.c.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    fun getCategoriesForProfile(profileId: Int): Flow<List<Category>> {
        return categoryRepository.getCategoriesForProfile(profileId)
    }

    fun createCategory(profileId: Int, name: String) {
        viewModelScope.launch {
            categoryRepository.createCategory(profileId, name)
        }
    }

    fun deleteCategory(categoryId: Int) {
        viewModelScope.launch {
            categoryRepository.deleteCategory(categoryId)
        }
    }
}