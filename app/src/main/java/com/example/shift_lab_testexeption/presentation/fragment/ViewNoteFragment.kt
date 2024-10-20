package com.example.shift_lab_testexeption.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shift_lab_testexeption.R
import com.example.shift_lab_testexeption.databinding.FragmentViewNoteBinding
import com.example.shift_lab_testexeption.domain.dto.NoteDto
import com.example.shift_lab_testexeption.presentation.viewmodel.NoteViewModel
import java.util.Calendar


class ViewNoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by activityViewModels()
    private lateinit var selectedNote: NoteDto
    private var _binding: FragmentViewNoteBinding? = null
    private val binding: FragmentViewNoteBinding
        get() = _binding!!
    private var statusNew: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentViewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (noteViewModel.note.value != null){
            statusNew = false
            selectedNote = noteViewModel.note.value!!
            binding.titleText.setText(selectedNote.title)
            binding.text.setText(selectedNote.text)
        }
        else{
            statusNew = true
            selectedNote = NoteDto(0,0,"","","")
        }

        binding.buttonBack.setOnClickListener {
            val note = selectedNote
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR).toString()
            val month = (calendar.get(Calendar.MONTH) + 1).toString()
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString()

            val date = "$day/$month/$year"

            if (note.title.isNotEmpty() && note.text.isNotEmpty()) {
                Log.i("MyLog", "$statusNew")

                if (statusNew){
                    Log.i("MyLog", "insert fragment1")
                    noteViewModel.insertNote(
                        NoteDto(
                            0,0,selectedNote.title,selectedNote.text,date
                        )
                    )
                }
                else {
                    Log.i("MyLog", "update")

                    noteViewModel.updateNote(id = selectedNote.id_note, tagId =  selectedNote.tagId,
                        title = selectedNote.title, text = selectedNote.text, dateUpdate = date )
                }
            }
            else if (note.title.isEmpty() && note.text.isNotEmpty()) {
                note.title = note.text.split(" ", limit = 4).firstOrNull() ?: ""
            }
            else if (note.title.isNotEmpty()){

                if (statusNew){
                    Log.i("MyLog", "insert fragment1")
                    noteViewModel.insertNote(
                        NoteDto(
                            0,0,selectedNote.title,selectedNote.text,date
                        )
                    )
                }
                else {
                    Log.i("MyLog", "update")

                    noteViewModel.updateNote(id = selectedNote.id_note, tagId =  selectedNote.tagId,
                        title = selectedNote.title, text = selectedNote.text, dateUpdate = date )
                }
            }


            findNavController().navigate(R.id.action_viewNoteFragment_to_listNoteFragment)
        }

        binding.titleText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                selectedNote.title = binding.titleText.text.toString()



            }
        }

        binding.text.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                selectedNote.text = binding.text.text.toString()

            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}