package com.prog7311.c.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prog7311.c.data.dao.*
import com.prog7311.c.data.entity.Category
import com.prog7311.c.data.entity.Entry
import com.prog7311.c.data.entity.Goal
import com.prog7311.c.data.entity.Profile
import com.prog7311.c.data.entity.User

@Database(
    entities = [
        User::class,
        Profile::class,
        Category::class,
        Entry::class,
        Goal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Room generates the actual implementation of these automatically
    abstract fun userDao(): UserDao
    abstract fun profileDao(): ProfileDao
    abstract fun categoryDao(): CategoryDao
    abstract fun entryDao(): EntryDao
    abstract fun goalDao(): GoalDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // If database already exists return it, otherwise create it
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cashflo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}