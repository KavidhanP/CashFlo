package com.prog7311.c.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prog7311.c.Goal

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    // Get goal for a specific month + year
    @Query("SELECT * FROM goals WHERE profileId = :profileId AND month = :month AND year = :year LIMIT 1")
    suspend fun getGoalForMonth(profileId: Int, month: Int, year: Int): Goal?

    // Get the most recently set goal (for displaying current target)
    @Query("SELECT * FROM goals WHERE profileId = :profileId ORDER BY year DESC, month DESC LIMIT 1")
    suspend fun getLatestGoal(profileId: Int): Goal?

    @Query("SELECT * FROM goals WHERE profileId = :profileId ORDER BY year DESC, month DESC")
    fun getAllGoalsForProfile(profileId: Int): LiveData<List<Goal>>

    @Query("DELETE FROM goals WHERE profileId = :profileId")
    suspend fun deleteAllGoalsForProfile(profileId: Int)
}