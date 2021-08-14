package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict =OnConflictStrategy.IGNORE)
    // suspend is ues in break ground word function
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from note_text order by id ASC")
    fun getAllNote(): LiveData<List<Note>>

}