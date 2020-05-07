package com.nanolabs.architecturetutorial

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.nanolabs.architecturetutorial.NoteDatabase.Companion.getAppDataBase

class NoteRepository2(application: Application?) {
    private val noteDao: NoteDao
    private val allNotes: LiveData<List<Note>>

    init {
        val database = getAppDataBase(application!!)
        noteDao = database!!.noteDao()
        allNotes = noteDao.getAllNotes()
    }

}