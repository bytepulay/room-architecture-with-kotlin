package com.nanolabs.architecturetutorial

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NoteViewModel(application: Application) :AndroidViewModel(application) {
    //var application = Application()
    private lateinit var noteRepository :NoteRepository
    private lateinit var allNotes:LiveData<List<Note>>
    private lateinit var allCounts:LiveData<Integer>

    init {
        val noteDao = NoteDatabase.getAppDataBase(application)?.noteDao()
        noteRepository = noteDao?.let { NoteRepository(it) }!!
        allNotes = noteRepository.allNote
        allCounts = noteRepository.allCount
    }

    fun insert(note:Note){
       noteRepository.insert(note)
    }

    fun update(note:Note){
       noteRepository.update(note)
    }

    fun delete(note:Note){
       noteRepository.delete(note)
    }


    fun deleteAllNotes(){
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes():LiveData<List<Note>>{
        return allNotes
    }

    fun getAllCount():LiveData<Integer>{
         return allCounts
    }
}