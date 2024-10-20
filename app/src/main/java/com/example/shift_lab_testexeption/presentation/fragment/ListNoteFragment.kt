package com.example.shift_lab_testexeption.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shift_lab_testexeption.R
import com.example.shift_lab_testexeption.databinding.FragmentListNoteBinding
import com.example.shift_lab_testexeption.presentation.adapter.NoteAdapter
import com.example.shift_lab_testexeption.presentation.adapter.TagAdapter
import com.example.shift_lab_testexeption.presentation.viewmodel.NoteViewModel
import com.example.shift_lab_testexeption.presentation.viewmodel.TagViewModel


class ListNoteFragment : Fragment() {

    private lateinit var binding: FragmentListNoteBinding

    private val noteViewModel: NoteViewModel by activityViewModels()
    private val tagViewModel: TagViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.getAllNotes()

        with(binding) {
            recyclerNoteView.layoutManager = LinearLayoutManager(requireContext())
            recyclerTagView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
                if (notes != null) {
                    Log.d("ListNoteFragment", "Notes size: ${notes.size}")
                    recyclerNoteView.adapter = NoteAdapter(notes, noteViewModel)
                }
            }

            tagViewModel.tags.observe(viewLifecycleOwner) { tags ->
                if (tags != null) {
                    Log.d("ListNoteFragment", "Tags size: ${tags.size}")
                    recyclerTagView.adapter = TagAdapter(tags)
                }
            }
        }


        binding.newNote.setOnClickListener {
            noteViewModel.clearSelectedNote()
            findNavController().navigate(R.id.action_listNoteFragment_to_viewNoteFragment)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            noteViewModel.getAllNotes()
            Log.d("ListNoteFragment", "Notes size: ${noteViewModel.notes.value?.size}")
        }







    }

}