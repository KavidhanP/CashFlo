package com.prog7311.c.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prog7311.c.data.dao.CategoryTotal
import com.prog7311.c.data.entity.Entry
import com.prog7311.c.repository.EntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EntryViewModel(private val entryRepository: EntryRepository) : ViewModel() {

    fun getEntriesForPeriod(profileId: Int, startDate: Long, endDate: Long): Flow<List<Entry>> {
        return entryRepository.getEntriesForPeriod(profileId, startDate, endDate)
    }

    fun getCategoryTotals(profileId: Int, startDate: Long, endDate: Long): Flow<List<CategoryTotal>> {
        return entryRepository.getCategoryTotals(profileId, startDate, endDate)
    }

    fun createEntry(
        profileId: Int,
        categoryId: Int?,
        description: String,
        amount: Double,
        date: Long,
        startTime: String,
        endTime: String,
        photoPath: String? = null
    ) {
        viewModelScope.launch {
            entryRepository.createEntry(
                profileId = profileId,
                categoryId = categoryId,
                description = description,
                amount = amount,
                date = date,
                startTime = startTime,
                endTime = endTime,
                photoPath = photoPath
            )
        }
    }

    fun deleteEntry(entryId: Int) {
        viewModelScope.launch {
            entryRepository.deleteEntry(entryId)
        }
    }
}