package com.prog7311.c.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prog7311.c.data.database.AppDatabase
import com.prog7311.c.repository.CategoryRepository
import com.prog7311.c.repository.EntryRepository
import com.prog7311.c.repository.GoalRepository
import com.prog7311.c.repository.ProfileRepository
import com.prog7311.c.repository.UserRepository

class ViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val userRepo = UserRepository(database.userDao())
        val profileRepo = ProfileRepository(database.profileDao())
        val categoryRepo = CategoryRepository(database.categoryDao())
        val entryRepo = EntryRepository(database.entryDao())
        val goalRepo = GoalRepository(database.goalDao())

        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(userRepo) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) ->
                ProfileViewModel(profileRepo) as T
            modelClass.isAssignableFrom(CategoryViewModel::class.java) ->
                CategoryViewModel(categoryRepo) as T
            modelClass.isAssignableFrom(EntryViewModel::class.java) ->
                EntryViewModel(entryRepo) as T
            modelClass.isAssignableFrom(GoalViewModel::class.java) ->
                GoalViewModel(goalRepo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}