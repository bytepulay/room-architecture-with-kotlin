package com.nanolabs.architecturetutorial

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Note::class),version = 1)
public  abstract class NoteDatabase : RoomDatabase() {
var i:Int = 0;
    public abstract fun noteDao():NoteDao

    companion object{
        var INSTANCE:NoteDatabase?=null
        var roomCallback = object :RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { PopulateDbAsyncTask(it).execute() }
            }
        }
        fun getAppDataBase(context: Context):NoteDatabase?{
            if (INSTANCE==null){
                synchronized(NoteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_database")
                        .addCallback(roomCallback)
                        .build()
                }
            }

            return INSTANCE
        }

    }

//    var roomCallback = object :RoomDatabase.Callback(){
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { PopulateDbAsyncTask(it).execute() }
//        }
//    }


    class PopulateDbAsyncTask(db:NoteDatabase) : AsyncTask<Void, Void, Void>() {
       private var noteDao:NoteDao = db.noteDao()
        override fun doInBackground(vararg params: Void?): Void? {
            var j:Int = 0
            for (i in 1..5){
                var note = Note()
                note.description = "description $i"
                note.title = "title $i"
                note.priority = i
                noteDao.insert(note)
            }

//            noteDao.insert(Note("Title 1","Description 1",1))
//            noteDao.insert(Note("Title 1","Description 1",2))
//            noteDao.insert(Note("Title 1","Description 1",3))
//            noteDao.insert(Note("Title 1","Description 1",3))
//            noteDao.insert(Note("Title 1","Description 1",5))
//            noteDao.insert(Note("Title 1","Description 1",6))
//            noteDao.insert(Note("Title 1","Description 1",7))
//            noteDao.insert(Note("Title 1","Description 1",8))
//            noteDao.insert(Note("Title 1","Description 1",9))
//            noteDao.insert(Note("Title 1","Description 1",10))
            return null
        }

    }



}