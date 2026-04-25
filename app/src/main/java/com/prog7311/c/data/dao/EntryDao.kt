package com.prog7311.c.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prog7311.c.data.entity.Entry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Insert
    suspend fun insertEntry(entry: Entry): Long

    // Get all entries for a profile filtered by date range
    @Query("SELECT * FROM entries WHERE profileId = :profileId AND date BETWEEN :startDate AND :endDate")
    fun getEntriesForPeriod(profileId: Int, startDate: Long, endDate: Long): Flow<List<Entry>>

    // Get total spent per category in a date range — for the category totals feature
    @Query("SELECT categoryId, SUM(amount) as total FROM entries WHERE profileId = :profileId AND date BETWEEN :startDate AND :endDate GROUP BY categoryId")
    fun getCategoryTotals(profileId: Int, startDate: Long, endDate: Long): Flow<List<CategoryTotal>>

    @Query("DELETE FROM entries WHERE entryId = :entryId")
    suspend fun deleteEntry(entryId: Int)
}

// Simple helper data class for the category totals query
data class CategoryTotal(
    val categoryId: Int?,
    val total: Double
)