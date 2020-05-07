package com.nanolabs.architecturetutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter():RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    private var listener: OnItemClickListener? = null
    companion object{

    }
    private var noteList:List<Note> = arrayListOf()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.text_view_title
        var textViewDescription = itemView.text_view_description
        var textPriority = itemView.text_view_priority


//        init {
//            itemView.setOnClickListener {
//
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentNote = noteList[position];
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textPriority.text = currentNote.id.toString()


    }

    fun setNote(noteList:List<Note>){
        this.noteList = noteList
        notifyDataSetChanged()
    }

    fun getNoteAt(position:Int):Note{
        return noteList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}