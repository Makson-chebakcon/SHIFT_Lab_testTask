package com.example.shift_lab_testexeption.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shift_lab_testexeption.R
import com.example.shift_lab_testexeption.domain.dto.TagDto

class TagAdapter(private var tagList: List<TagDto>): RecyclerView.Adapter<TagAdapter.TagViewHolder>() {


    class TagViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById<TextView>(R.id.nameTagView)

        fun bind(tag: TagDto){
            name.text = tag.tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.card_tag, parent, false)
        return TagViewHolder(view).apply {
            itemView.setOnClickListener {

            }
        }
    }

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagList[position])
    }
}