package com.prog7311.c.repository

import com.prog7311.c.data.dao.GoalDao
import com.prog7311.c.data.entity.Goal
import kotlinx.coroutines.flow.Flow

class GoalRepository(private val goalDao: GoalDao) {

    suspend fun setGoal(profileId: Int, minAmount: Double, maxAmount: Double, month: Int, year: Int): Long {
        val goal = Goal(
            profileId = profileId,
            minAmount = minAmount,
            maxAmount = maxAmount,
            month = month,
            year = year
        )
        return goalDao.insertOrUpdateGoal(goal)
    }

    fun getGoalForMonth(profileId: Int, month: Int, year: Int): Flow<Goal?> {
        return goalDao.getGoalForMonth(profileId, month, year)
    }
}