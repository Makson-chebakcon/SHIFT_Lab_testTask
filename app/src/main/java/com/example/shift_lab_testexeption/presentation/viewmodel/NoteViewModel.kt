package com.example.shift_lab_testexeption.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shift_lab_testexeption.domain.dto.NoteDto
import com.example.shift_lab_testexeption.domain.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository): ViewModel() {

    private val _notes = MutableLiveData<List<NoteDto>>()

    private val _note = MutableLiveData<NoteDto?>()

    val notes: LiveData<List<NoteDto>> = _notes

    val note: LiveData<NoteDto?> = _note

    init{
        loadAllNotes()
    }

    fun setNote(noteDto: NoteDto){
        _note.value = noteDto
    }

    fun clearSelectedNote() {
        _note.value = null
    }


    private fun loadAllNotes(){
        viewModelScope.launch {
            try {
                _notes.value = noteRepository.getNotes()
            }
            catch (e: Exception){
                Log.e("NoteViewModel", "Error geting note ", e)
            }

        }
    }

    fun getAllNotes(){
        loadAllNotes()
    }

    fun updateNote(id:Long = 0, tagId:Long = 0, title: String = "Скрытое содрежимое",text:String, dateUpdate: String ){
        viewModelScope.launch {
            try {
                noteRepository.updateNote(
                    noteDto = NoteDto(
                        id_note = id,
                        tagId = tagId,
                        title = title,
                        text = text,
                        dateUpdate = dateUpdate
                    ))
            }
            catch (e: Exception){
                Log.e("NoteViewModel", "Error update note")
            }
        }
    }

    fun insertNote(noteDto: NoteDto){
        viewModelScope.launch {
            try {
                noteRepository.insertNote(noteDto = noteDto)
            }
            catch (e: Exception){
                Log.e("NoteViewModel", "Error insert note")
            }
        }
    }






}