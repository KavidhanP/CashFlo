package com.prog7311.c.repository
import com.prog7311.c.data.dao.CategoryTotal
import com.prog7311.c.data.dao.EntryDao
import com.prog7311.c.data.entity.Entry
import kotlinx.coroutines.flow.Flow

class EntryRepository(private val entryDao: EntryDao) {

    suspend fun createEntry(
        profileId: Int,
        categoryId: Int?,
        description: String,
        amount: Double,
        date: Long,
        startTime: String,
        endTime: String,
        photoPath: String? = null
    ): Long {
        val entry = Entry(
            profileId = profileId,
            categoryId = categoryId,
            description = description,
            amount = amount,
            date = date,
            startTime = startTime,
            endTime = endTime,
            photoPath = photoPath
        )
        return entryDao.insertEntry(entry)
    }

    fun getEntriesForPeriod(profileId: Int, startDate: Long, endDate: Long): Flow<List<Entry>> {
        return entryDao.getEntriesForPeriod(profileId, startDate, endDate)
    }
    fun getCategoryTotals(profileId: Int, startDate: Long, endDate: Long): Flow<List<CategoryTotal>> {
        return entryDao.getCategoryTotals(profileId, startDate, endDate)
    }

    suspend fun deleteEntry(entryId: Int) {
        entryDao.deleteEntry(entryId)
    }
}