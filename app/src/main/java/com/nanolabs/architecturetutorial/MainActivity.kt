package com.nanolabs.architecturetutorial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
    lateinit var noteViewModel:NoteViewModel
    lateinit var recyclerView:RecyclerView
    companion object{
        var adapter = NoteAdapter()
        var ADD_NOTE_REQUEST=1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //noteViewModel = ViewModelProvider(this).get(NoteViewModel1::class.java)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        supportActionBar!!.title = noteViewModel.getAllCount().toString()

        //Log.d("COUNT",noteViewModel.getAllCount().toString())
     noteViewModel.getAllCount().observe(this, Observer {
         supportActionBar!!.title = it.toString()
     })

        button_add_note.setOnClickListener {
            startActivityForResult(Intent(this,AddNoteActivity::class.java), ADD_NOTE_REQUEST)
        }


        noteViewModel.getAllNotes().observe(this, Observer {
            //update Recycler View
             adapter.setNote(it)
            recyclerView.adapter = adapter
            Toast.makeText(applicationContext,"on Change",Toast.LENGTH_LONG).show()
        })

        //noteViewModel.getAllCount()

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(applicationContext,"Note Delete",Toast.LENGTH_LONG).show()
            }
        }).attachToRecyclerView(recyclerView)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK){
            var title = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            var description = data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            var priority = data?.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1)

            var note = Note()
            note.title = title
            note.description = description
            note.priority = priority
            noteViewModel.insert(note)

            Toast.makeText(applicationContext,"Note Saved" + title + description + priority ,Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(applicationContext,"Note not  Saved" ,Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_note -> {
               noteViewModel.deleteAllNotes()
                Toast.makeText(applicationContext,"all note is delete ",Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

}
