package com.prog7311.c.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prog7311.c.data.entity.Goal
import com.prog7311.c.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GoalViewModel(private val goalRepository: GoalRepository) : ViewModel() {

    fun getGoalForMonth(profileId: Int, month: Int, year: Int): Flow<Goal?> {
        return goalRepository.getGoalForMonth(profileId, month, year)
    }

    fun setGoal(
        profileId: Int,
        minAmount: Double,
        maxAmount: Double,
        month: Int,
        year: Int
    ) {
        viewModelScope.launch {
            goalRepository.setGoal(profileId, minAmount, maxAmount, month, year)
        }
    }
}