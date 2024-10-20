package com.example.shift_lab_testexeption.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shift_lab_testexeption.R
import com.example.shift_lab_testexeption.domain.AppDatabase
import com.example.shift_lab_testexeption.domain.dao.TagDao
import com.example.shift_lab_testexeption.domain.dto.TagDto
import com.example.shift_lab_testexeption.domain.repository.NoteRepository
import com.example.shift_lab_testexeption.domain.repository.TagRepository
import com.example.shift_lab_testexeption.presentation.viewmodel.NoteViewModel
import com.example.shift_lab_testexeption.presentation.viewmodel.NoteViewModelFactory
import com.example.shift_lab_testexeption.presentation.viewmodel.TagViewModel
import com.example.shift_lab_testexeption.presentation.viewmodel.TagViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var tagViewModel: TagViewModel
    private lateinit var tagDao: TagDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getDatabase(this)
        var noteRepository = NoteRepository(database.noteDao())
        val notefactory  = NoteViewModelFactory(noteRepository = noteRepository)
        noteViewModel = ViewModelProvider(this, notefactory)[NoteViewModel::class.java]

        var tagRepository = TagRepository(database.tagDao())
        val tagfactory  = TagViewModelFactory(tagRepository = tagRepository)
        tagViewModel = ViewModelProvider(this, tagfactory)[TagViewModel::class.java]

        tagViewModel.tags.observe(this) { tags ->
            if (tags.isNullOrEmpty()) {
                tagViewModel.insertTag(TagDto(0, "Всё"))
            }
        }




    }
}