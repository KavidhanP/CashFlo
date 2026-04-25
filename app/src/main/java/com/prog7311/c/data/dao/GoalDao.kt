package com.prog7311.c.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prog7311.c.data.entity.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateGoal(goal: Goal): Long

    @Query("SELECT * FROM goals WHERE profileId = :profileId AND month = :month AND year = :year LIMIT 1")
    fun getGoalForMonth(profileId: Int, month: Int, year: Int): Flow<Goal?>
}