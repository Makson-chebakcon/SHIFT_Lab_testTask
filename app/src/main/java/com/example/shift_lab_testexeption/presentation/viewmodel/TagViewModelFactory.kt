package com.example.shift_lab_testexeption.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shift_lab_testexeption.domain.repository.TagRepository

class TagViewModelFactory(private val tagRepository: TagRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TagViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TagViewModel(tagRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}