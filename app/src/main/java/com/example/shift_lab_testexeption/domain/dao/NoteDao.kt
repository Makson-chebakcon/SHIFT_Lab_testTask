package com.example.shift_lab_testexeption.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Update
import com.example.shift_lab_testexeption.domain.dto.NoteDto

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDto: NoteDto)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM note_table")
    suspend fun getNote():List<NoteDto>

    @Update
    suspend fun updateNote(noteDto: NoteDto)


}