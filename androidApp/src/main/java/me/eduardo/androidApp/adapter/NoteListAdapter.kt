package me.eduardo.androidApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import db.Notes
import me.eduardo.androidApp.R
import me.eduardo.androidApp.databinding.NoteListItemBinding


class NoteListAdapter(val notes: List<Notes>, val click: (Long) -> Unit) :
    RecyclerView.Adapter<NoteListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false)

        return NoteListViewHolder(view)

    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {

        holder.bind(notes[position], click)

    }

    override fun getItemCount(): Int {
        return notes.size
    }
}


class NoteListViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    private val binding = NoteListItemBinding.bind(item)

    fun bind(note: Notes, click: (Long) -> Unit) {

        binding.tvNoteTitle.text = note.title
        binding.root.setOnClickListener {
            click(note.id)
        }

    }

}