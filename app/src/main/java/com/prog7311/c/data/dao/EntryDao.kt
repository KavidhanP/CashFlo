package com.prog7311.c.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prog7311.c.Entry

// Used for the "category totals in period" feature
data class CategoryTotal(
    val categoryName: String,
    val totalAmount: Double,
    val entryCount: Int
)

// Used for Excel export — full row with category name resolved
data class EntryExportRow(
    val entryId: Int,
    val description: String,
    val amount: Double,
    val date: Long,
    val startTime: String,
    val endTime: String,
    val categoryName: String?,
    val photoPath: String?
)

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: Entry): Long

    @Update
    suspend fun updateEntry(entry: Entry)

    @Delete
    suspend fun deleteEntry(entry: Entry)

    @Query("SELECT * FROM entries WHERE entryId = :entryId LIMIT 1")
    suspend fun getEntryById(entryId: Int): Entry?

    // Feature: View list of entries in a user-selectable period
    // date is stored as Unix millis (Long), so we compare Long range
    @Query("""
        SELECT * FROM entries 
        WHERE profileId = :profileId 
          AND date BETWEEN :startMs AND :endMs 
        ORDER BY date DESC
    """)
    fun getEntriesInPeriod(
        profileId: Int,
        startMs: Long,
        endMs: Long
    ): LiveData<List<Entry>>

    @Query("""
        SELECT * FROM entries 
        WHERE profileId = :profileId 
          AND date BETWEEN :startMs AND :endMs 
        ORDER BY date DESC
    """)
    suspend fun getEntriesInPeriodOnce(
        profileId: Int,
        startMs: Long,
        endMs: Long
    ): List<Entry>

    //  Feature: View category totals in a user-selectable period
    @Query("""
        SELECT c.name AS categoryName,
               COALESCE(SUM(e.amount), 0.0) AS totalAmount,
               COUNT(e.entryId) AS entryCount
        FROM categories c
        LEFT JOIN entries e
            ON e.categoryId = c.categoryId
           AND e.profileId = :profileId
           AND e.date BETWEEN :startMs AND :endMs
        WHERE c.profileId = :profileId
        GROUP BY c.categoryId, c.name
        ORDER BY totalAmount DESC
    """)
    fun getCategoryTotalsInPeriod(
        profileId: Int,
        startMs: Long,
        endMs: Long
    ): LiveData<List<CategoryTotal>>

    @Query("""
        SELECT c.name AS categoryName,
               COALESCE(SUM(e.amount), 0.0) AS totalAmount,
               COUNT(e.entryId) AS entryCount
        FROM categories c
        LEFT JOIN entries e
            ON e.categoryId = c.categoryId
           AND e.profileId = :profileId
           AND e.date BETWEEN :startMs AND :endMs
        WHERE c.profileId = :profileId
        GROUP BY c.categoryId, c.name
        ORDER BY totalAmount DESC
    """)
    suspend fun getCategoryTotalsInPeriodOnce(
        profileId: Int,
        startMs: Long,
        endMs: Long
    ): List<CategoryTotal>

    //Total spent in period — used for goal/gamification checks
    @Query("""
        SELECT COALESCE(SUM(amount), 0.0) FROM entries
        WHERE profileId = :profileId
          AND date BETWEEN :startMs AND :endMs
    """)
    suspend fun getTotalSpentInPeriod(profileId: Int, startMs: Long, endMs: Long): Double

    // Excel export: entries joined with category name
    @Query("""
        SELECT e.entryId, e.description, e.amount, e.date, e.startTime, e.endTime,
               c.name AS categoryName, e.photoPath
        FROM entries e
        LEFT JOIN categories c ON e.categoryId = c.categoryId
        WHERE e.profileId = :profileId
          AND e.date BETWEEN :startMs AND :endMs
        ORDER BY e.date DESC
    """)
    suspend fun getEntriesForExport(
        profileId: Int,
        startMs: Long,
        endMs: Long
    ): List<EntryExportRow>

    //Entries with photos (for photo access from list)
    @Query("""
        SELECT * FROM entries
        WHERE profileId = :profileId
          AND photoPath IS NOT NULL
          AND date BETWEEN :startMs AND :endMs
        ORDER BY date DESC
    """)
    fun getEntriesWithPhotosInPeriod(
        profileId: Int,
        startMs: Long,
        endMs: Long
    ): LiveData<List<Entry>>


    @Query("DELETE FROM entries WHERE profileId = :profileId")
    suspend fun deleteAllEntriesForProfile(profileId: Int)

    @Query("DELETE FROM entries WHERE categoryId = :categoryId")
    suspend fun deleteEntriesForCategory(categoryId: Int)
}