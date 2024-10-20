package com.example.shift_lab_testexeption.domain.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tag_table")
data class TagDto(
    @PrimaryKey(autoGenerate = true) val id_tag:Long =0,
    @ColumnInfo(name = "tag") val tag: String,
)
