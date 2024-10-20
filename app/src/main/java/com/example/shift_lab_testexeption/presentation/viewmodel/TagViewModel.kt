package com.example.shift_lab_testexeption.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shift_lab_testexeption.domain.dto.TagDto
import com.example.shift_lab_testexeption.domain.repository.TagRepository
import kotlinx.coroutines.launch

class TagViewModel(private val tagRepository: TagRepository): ViewModel() {

    private val _tags = MutableLiveData<List<TagDto>>()
    private val _tag = MutableLiveData<TagDto>()

    val tags: LiveData<List<TagDto>> = _tags
    val tag: LiveData<TagDto> = _tag

    init{
        loadAllTags()
    }

    private fun loadAllTags(){
        viewModelScope.launch {
            try {
                _tags.value = tagRepository.getAllTag()
            }
            catch (e: Exception){
                Log.e("TagViewModel", "Error geting tags ", e)
            }

        }
    }

    fun getAllTags() = loadAllTags()

    fun insertTag(tagDto:TagDto){
        viewModelScope.launch {
            try {
                tagRepository.insertTag(tagDto = tagDto)

            }
            catch(e: Exception){
                Log.e("TagViewModel", "Error insert tag")
            }

        }
    }

    fun getTag(idTag: Long){
        viewModelScope.launch{
            try {
                _tag.value = tagRepository.getTag(idTag)
            }
            catch (e: Exception){
                Log.e("TagViewModel", "Error get to id tag")
            }

        }
    }


}