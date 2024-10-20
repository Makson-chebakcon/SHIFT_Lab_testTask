package com.example.shift_lab_testexeption.domain.repository

import android.util.Log
import com.example.shift_lab_testexeption.domain.dao.NoteDao
import com.example.shift_lab_testexeption.domain.dto.NoteDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private var noteDao: NoteDao){

    suspend fun insertNote(noteDto: NoteDto){
        try {
            withContext(Dispatchers.IO) {
                Log.i("MyLog", noteDto.toString())
                noteDao.insert(noteDto)
                Log.i("MyLog", "insert")
            }
        }
        catch (e: Exception) { // Исправлено
            Log.e("DatabaseError", "Error inserting note: ${e.message}")
        }
    }

    suspend fun getNotes(): List<NoteDto>?{
        return try {
            withContext(Dispatchers.IO) {
                noteDao.getNote()
            }
        }
        catch (e: Exception){
            Log.e("NoteRepository", "Error getting note", e)
            null
        }
    }

    suspend fun updateNote(noteDto: NoteDto){
        try {
            withContext(Dispatchers.IO) {
                noteDao.updateNote(noteDto)
                Log.e("Database", "Insert note complete")
            }
        }
        catch (e: Exception) { // Исправлено
            Log.e("DatabaseError", "Error inserting note: ${e.message}")
        }
    }




}