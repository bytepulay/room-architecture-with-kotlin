package com.nanolabs.architecturetutorial

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
 public interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note:Note)

    @Delete
    fun delete(note:Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNote()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>
//
   @Query("SELECT COUNT(*) FROM note_table")
   fun getAllCount():LiveData<Integer>
}