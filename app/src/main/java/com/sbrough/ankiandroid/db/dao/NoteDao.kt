package com.sbrough.ankiandroid.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.sbrough.ankiandroid.db.entities.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id=:noteId")
    fun getNoteWithId(noteId: Long): Note
}