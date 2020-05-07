package com.nanolabs.architecturetutorial;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository1 {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    public NoteRepository1(Application application) {
        NoteDatabase database = NoteDatabase.Companion.getAppDataBase(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    private static class InsertAsyncTask extends AsyncTask<Note,Void,Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }


}
