package com.nanolabs.architecturetutorial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {
   // private var listener:OnItemClickListener? = null
  companion object{
   var  EXTRA_TITLE = "com.nanolabs.architecturetutorial.EXTRA_TITLE"
    var  EXTRA_DESCRIPTION = "com.nanolabs.architecturetutorial.EXTRA_DESCRIPTION"
    var  EXTRA_PRIORITY= "com.nanolabs.architecturetutorial.EXTRA_PRIORITY"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        number_picker_priority.minValue =1
        number_picker_priority.maxValue =10
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
        supportActionBar?.title = "Add Note"


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    private fun saveNote() {
        var title:String= edit_text_title.text.toString()
        var description:String = edit_text_description.text.toString()
        var priority:Int = number_picker_priority.value

        if (title.isEmpty() || description.isEmpty()){
            Toast.makeText(applicationContext,"insert and description ",Toast.LENGTH_LONG).show()
            return
        }

        var intent = Intent()
        intent.putExtra(EXTRA_TITLE,title)
        intent.putExtra(EXTRA_DESCRIPTION,description)
        intent.putExtra(EXTRA_PRIORITY,priority)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

//    interface OnItemClickListener {
//        fun onItemClick(note: Note?)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener?) {
//            this.listener = listener
//    }

}
