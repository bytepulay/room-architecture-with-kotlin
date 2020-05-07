package com.nanolabs.architecturetutorial

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

//    lateinit var noteDao:NoteDao = noteDao
   var allNote: LiveData<List<Note>> =noteDao.getAllNotes()
    var allCount: LiveData<Integer> =noteDao.getAllCount()

//
//    init {
//        val database = NoteDatabase.getAppDataBase(application)
//        noteDao = database!!.noteDao()
//        allNote = noteDao.getAllNotes()
//    }

    fun insert(note:Note){
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note:Note){
            UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note:Note){
            DeleteNoteAsyncTask(noteDao).execute(note)
    }


    fun deleteAllNotes(){
            DeleteAllNoteAsyncTask(noteDao).execute()
    }

    fun getAllNotes():LiveData<List<Note>>{
        return allNote
    }

//    fun getAllCount():LiveData<Integer>{
//       return allCount
//    }
    fun AllCount():LiveData<Integer>{
    return allCount
}


    private class InsertNoteAsyncTask(noteDao:NoteDao):AsyncTask<Note,Void,Void>(){
        private var noteDao:NoteDao = noteDao

        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.insert(it) }
            return null
        }

    }

    private class UpdateNoteAsyncTask(noteDao:NoteDao):AsyncTask<Note,Void,Void>(){
        private var noteDao:NoteDao = noteDao

        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.update(it) }
            return null
        }

    }



    private class DeleteNoteAsyncTask(noteDao:NoteDao):AsyncTask<Note,Void,Void>(){
        private var noteDao:NoteDao = noteDao

        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.delete(it) }
            return null
        }

    }


    private class DeleteAllNoteAsyncTask(noteDao:NoteDao):AsyncTask<Void,Void,Void>(){
        private var noteDao:NoteDao = noteDao

        override fun doInBackground(vararg voids: Void?): Void? {
           noteDao.deleteAllNote()
            return null
        }

    }

//    private class GetAllCountAsyncTask(noteDao:NoteDao):AsyncTask<Void,Void,LiveData<Int>>(){
//        private var noteDao:NoteDao = noteDao
//
//        override fun doInBackground(vararg voids: Void?): LiveData<Int>? {
//            return  noteDao.getAllCount()
//
//        }
//
//    }

}