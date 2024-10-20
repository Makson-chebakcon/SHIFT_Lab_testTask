package com.example.shift_lab_testexeption.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shift_lab_testexeption.R
import com.example.shift_lab_testexeption.domain.dto.NoteDto
import com.example.shift_lab_testexeption.presentation.viewmodel.NoteViewModel

class NoteAdapter(private var noteList: List<NoteDto>,
                  private val noteViewModel: NoteViewModel)
    : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val textTitle: TextView = itemView.findViewById<TextView>(R.id.titleNote)
        private val textNote: TextView = itemView.findViewById<TextView>(R.id.textNote)
        private val textData: TextView = itemView.findViewById<TextView>(R.id.textDate)
        private val textTag: TextView = itemView.findViewById<TextView>(R.id.textTag)

        fun bind(note: NoteDto){
            textTitle.text = note.title
            textNote.text = note.text
            textTag.text = note.tagId.toString()
            textData.text = note.dateUpdate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.card_note, parent, false)
        return NoteViewHolder(view).apply {
            itemView.setOnClickListener {
                val navController = androidx.navigation.Navigation.findNavController(itemView)
                navController.navigate(R.id.action_listNoteFragment_to_viewNoteFragment)
                noteViewModel.setNote(noteList[adapterPosition])
            }
        }
    }
    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }
}