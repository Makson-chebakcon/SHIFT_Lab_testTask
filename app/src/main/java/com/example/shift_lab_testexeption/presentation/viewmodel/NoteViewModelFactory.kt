package com.example.shift_lab_testexeption.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shift_lab_testexeption.domain.repository.NoteRepository

class NoteViewModelFactory(private var noteRepository: NoteRepository): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}