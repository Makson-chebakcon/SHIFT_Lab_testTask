package com.example.shift_lab_testexeption.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shift_lab_testexeption.domain.dto.TagDto

@Dao
interface TagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg tagDto: TagDto)

    @Query("SELECT * FROM tag_table")
    suspend fun getAllTag(): List<TagDto>

    @Query("SELECT * FROM tag_table WHERE id_tag = :tag_Id LIMIT 1")
    suspend fun getTag(tag_Id: Long): TagDto

    @Query("DELETE FROM tag_table WHERE id_tag = :tagId")
    suspend fun deleteTagById(tagId: Long)

}