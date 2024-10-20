package com.example.shift_lab_testexeption.domain.repository

import android.util.Log
import com.example.shift_lab_testexeption.domain.dao.TagDao
import com.example.shift_lab_testexeption.domain.dto.TagDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TagRepository(private var tagDao: TagDao) {

    suspend fun insertTag(tagDto: TagDto){
        try {
            withContext(Dispatchers.IO) {
                tagDao.insert(tagDto)
                Log.e("Database", "Insert note complete")
            }
        }
        catch (e: Exception) { // Исправлено
            Log.e("DatabaseError", "Error inserting tag: ${e.message}")
        }

    }

    suspend fun getAllTag(): List<TagDto>?{
        return try {
            withContext(Dispatchers.IO) {
                tagDao.getAllTag()
            }
        }
        catch (e: Exception){
            Log.e("UserRepository", "Error getting all tags", e)
            null
        }

    }

    suspend fun getTag(tag_Id: Long): TagDto?{
        return try {
            withContext(Dispatchers.IO) {
                tagDao.getTag(tag_Id = tag_Id)
            }
        }
        catch (e: Exception){
            Log.e("UserRepository", "Error getting tag", e)
            null
        }
    }
}