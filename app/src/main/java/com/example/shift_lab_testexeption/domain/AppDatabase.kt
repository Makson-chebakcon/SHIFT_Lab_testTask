package com.example.shift_lab_testexeption.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shift_lab_testexeption.domain.dao.NoteDao
import com.example.shift_lab_testexeption.domain.dao.TagDao
import com.example.shift_lab_testexeption.domain.dto.NoteDto
import com.example.shift_lab_testexeption.domain.dto.TagDto

@Database(entities = [NoteDto::class, TagDto::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun tagDao(): TagDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance

            }
        }
    }



}